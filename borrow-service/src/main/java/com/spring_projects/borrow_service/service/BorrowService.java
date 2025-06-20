package com.spring_projects.borrow_service.service;


import com.spring_projects.borrow_service.dto.BorrowRequestDto;
import com.spring_projects.borrow_service.dto.BorrowResponseDto;
import com.spring_projects.borrow_service.entity.Borrow;
import com.spring_projects.borrow_service.entity.BorrowStatus;
import com.spring_projects.borrow_service.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;

    private final String STUDENT_API_URL = "http://localhost:8081/api/students/";
    private final String BOOK_API_URL = "http://localhost:8082/api/books/";

    public BorrowResponseDto borrowBook(BorrowRequestDto dto) {


        try {
            restTemplate.getForObject(STUDENT_API_URL + dto.getStudentId(), Object.class);
        } catch (Exception e) {
            throw new RuntimeException("Student not found in student-service");
        }


        try {
            restTemplate.getForObject(BOOK_API_URL + dto.getBookId(), Object.class);
        } catch (Exception e) {
            throw new RuntimeException("Book not found in book-service");
        }


        String url = BOOK_API_URL + dto.getBookId() + "/update-copies?delta=-1";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers); // body is null, but required

        try {
            restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Void.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update available copies in book-service: " + e.getMessage());
        }


        Borrow borrow= new Borrow();
        borrow.setStudentId(dto.getStudentId());
        borrow.setBookId(dto.getBookId());
        borrow.setBorrowDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusDays(14));
        borrow.setStatus(BorrowStatus.BORROWED);


        Borrow saved = borrowRepository.save(borrow);
        return modelMapper.map(saved, BorrowResponseDto.class);
    }

    public BorrowResponseDto returnBook(Long borrowId){

        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found with ID: " + borrowId));

        if (borrow.getReturnDate() != null) {
            throw new RuntimeException("Book has already been returned.");
        }

        borrow.setReturnDate(LocalDate.now());
        borrow.setStatus(BorrowStatus.RETURNED);

        String url = BOOK_API_URL + borrow.getBookId() + "/update-copies?delta=1";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        try {
            restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Void.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update available copies in book-service: " + e.getMessage());
        }

        Borrow updated = borrowRepository.save(borrow);
        return modelMapper.map(updated, BorrowResponseDto.class);

    }
    public BorrowResponseDto getBorrowById(Long id) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found with ID: " + id));
        return modelMapper.map(borrow, BorrowResponseDto.class);
    }

    public List<BorrowResponseDto> getAllBorrows() {
        List<Borrow> borrows = borrowRepository.findAll();
        List<BorrowResponseDto> responseList = new ArrayList<>();

        for (Borrow borrow : borrows) {
            BorrowResponseDto dto = modelMapper.map(borrow, BorrowResponseDto.class);
            responseList.add(dto);
        }

        return responseList;
    }









}



