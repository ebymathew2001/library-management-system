package com.spring_projects.borrow_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRequestDto {
    private Long studentId;
    private Long bookId;
}
