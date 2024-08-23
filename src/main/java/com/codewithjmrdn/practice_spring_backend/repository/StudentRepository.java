package com.codewithjmrdn.practice_spring_backend.repository;

import com.codewithjmrdn.practice_spring_backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
