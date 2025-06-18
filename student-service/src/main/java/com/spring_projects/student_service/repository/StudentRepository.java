package com.spring_projects.student_service.repository;

import com.spring_projects.student_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByAdmissionNumber(String admissionNumber);
    boolean existsByEmail(String email);
    Optional<Student> findByAdmissionNumber(String admissionNumber);
    Optional<Student> findByEmail(String email);
}
