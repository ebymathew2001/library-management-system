package com.spring_project.book_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @Column(unique = true, nullable = false)
    private String isbn;

    private Integer totalCopies;

    private Integer availableCopies;
}
