package com.spring_projects.student_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class StudentRequestDto {
    private String name;
    private String admissionNumber;
    private String department;
    private String email;

    // Getters and Setters
}
