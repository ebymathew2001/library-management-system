package com.spring_projects.student_service.controller;

import com.spring_projects.student_service.dto.StudentRequestDto;
import com.spring_projects.student_service.dto.StudentResponseDto;
import com.spring_projects.student_service.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {


    private final StudentService studentService;



    @PostMapping
    public ResponseEntity<StudentResponseDto> addStudent(@RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto created = studentService.addStudent(studentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id,
                                                            @RequestBody StudentRequestDto dto) {
        StudentResponseDto updated = studentService.updateStudent(id, dto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully.");
    }




}
