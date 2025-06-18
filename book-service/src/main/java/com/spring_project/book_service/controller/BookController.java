package com.spring_project.book_service.controller;

import com.spring_project.book_service.dto.BookRequestDto;
import com.spring_project.book_service.dto.BookResponseDto;
import com.spring_project.book_service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto dto) {
        BookResponseDto created = bookService.addBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id,
                                                      @RequestBody BookRequestDto dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/update-copies")
    public ResponseEntity<BookResponseDto> updateCopies(@PathVariable Long id,
                                                        @RequestParam int delta) {
        return ResponseEntity.ok(bookService.updateAvailableCopies(id, delta));
    }








}
