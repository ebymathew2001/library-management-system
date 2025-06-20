package com.spring_projects.borrow_service.controller;


import com.spring_projects.borrow_service.dto.BorrowRequestDto;
import com.spring_projects.borrow_service.dto.BorrowResponseDto;
import com.spring_projects.borrow_service.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity<BorrowResponseDto> borrowBook(@RequestBody BorrowRequestDto dto){
        BorrowResponseDto created = borrowService.borrowBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowResponseDto> returnBook(@PathVariable Long id) {
        BorrowResponseDto updatedBorrow = borrowService.returnBook(id);
        return ResponseEntity.ok(updatedBorrow);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowResponseDto> getBorrowById(@PathVariable Long id) {
        BorrowResponseDto dto = borrowService.getBorrowById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BorrowResponseDto>> getAllBorrows() {
        List<BorrowResponseDto> all = borrowService.getAllBorrows();
        return ResponseEntity.ok(all);
    }











}
