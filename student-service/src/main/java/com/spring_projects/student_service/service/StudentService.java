package com.spring_projects.student_service.service;

import com.spring_projects.student_service.dto.StudentRequestDto;
import com.spring_projects.student_service.dto.StudentResponseDto;
import com.spring_projects.student_service.entity.Student;
import com.spring_projects.student_service.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentResponseDto addStudent(StudentRequestDto dto) {

        if (studentRepository.existsByAdmissionNumber(dto.getAdmissionNumber())) {
            throw new RuntimeException("Student with this admission number already exists");
        }

        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Student with this email already exists");
        }

        Student student = modelMapper.map(dto, Student.class);
        Student saved = studentRepository.save(student);

        return modelMapper.map(saved, StudentResponseDto.class);
    }

    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDto> responseList = new ArrayList<>();

        for (Student student : students) {
            responseList.add(modelMapper.map(student, StudentResponseDto.class));
        }

        return responseList;
    }

    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return modelMapper.map(student, StudentResponseDto.class);
    }

    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));


        Optional<Student> studentByAdmission = studentRepository.findByAdmissionNumber(dto.getAdmissionNumber());
        if (studentByAdmission.isPresent() && !studentByAdmission.get().getId().equals(student.getId())) {
            throw new RuntimeException("Admission number already used by another student");
        }


        Optional<Student> studentByEmail = studentRepository.findByEmail(dto.getEmail());
        if (studentByEmail.isPresent() && !studentByEmail.get().getId().equals(student.getId())) {
            throw new RuntimeException("Email already used by another student");
        }


        student.setName(dto.getName());
        student.setAdmissionNumber(dto.getAdmissionNumber());
        student.setDepartment(dto.getDepartment());
        student.setEmail(dto.getEmail());

        Student updated = studentRepository.save(student);
        return modelMapper.map(updated, StudentResponseDto.class);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }




}









