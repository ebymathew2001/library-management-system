package com.spring_project.book_service.service;


import com.spring_project.book_service.dto.BookRequestDto;
import com.spring_project.book_service.dto.BookResponseDto;
import com.spring_project.book_service.entity.Book;
import com.spring_project.book_service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookResponseDto addBook(BookRequestDto dto) {
        if (bookRepository.existsByIsbn(dto.getIsbn())) {
            throw new RuntimeException("Book with this ISBN already exists");
        }

        Book book = modelMapper.map(dto, Book.class);
        Book saved = bookRepository.save(book);
        return modelMapper.map(saved, BookResponseDto.class);
    }

    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponseDto> responseList = new ArrayList<>();

        for (Book book : books) {
            BookResponseDto dto = modelMapper.map(book, BookResponseDto.class);
            responseList.add(dto);
        }

        return responseList;
    }

    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
        return modelMapper.map(book, BookResponseDto.class);
    }

    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setIsbn(dto.getIsbn());
        existing.setTotalCopies(dto.getTotalCopies());


        existing.setAvailableCopies(dto.getAvailableCopies());

        Book updated = bookRepository.save(existing);
        return modelMapper.map(updated, BookResponseDto.class);


    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }

    public BookResponseDto updateAvailableCopies(Long id, int delta) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        int newAvailable = book.getAvailableCopies() + delta;

        if (newAvailable < 0) {
            throw new RuntimeException("Available copies cannot be negative");
        }
        if (newAvailable > book.getTotalCopies()) {
            throw new RuntimeException("Available copies cannot exceed total copies");
        }


        book.setAvailableCopies(newAvailable);
        Book saved = bookRepository.save(book);
        return modelMapper.map(saved, BookResponseDto.class);
    }






}
