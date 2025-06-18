package com.spring_projects.borrow_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Long bookId;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private BorrowStatus status;
}
