package com.spring_projects.borrow_service.repository;


import com.spring_projects.borrow_service.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByStudentId(Long studentId);
}
