package com.spring_project.book_service.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private Integer totalCopies;

    private Integer availableCopies;
}
