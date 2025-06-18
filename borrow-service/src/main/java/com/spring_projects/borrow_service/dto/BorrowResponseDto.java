package com.spring_projects.borrow_service.dto;


import com.spring_projects.borrow_service.entity.BorrowStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowResponseDto {
    private Long id;
    private Long studentId;
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BorrowStatus status;
}
