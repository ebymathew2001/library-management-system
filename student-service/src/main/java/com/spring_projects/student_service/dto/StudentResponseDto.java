package com.spring_projects.student_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String name;
    private String admissionNumber;
    private String department;
    private String email;

}
