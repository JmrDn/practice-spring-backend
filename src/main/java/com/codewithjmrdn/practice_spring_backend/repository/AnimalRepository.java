package com.codewithjmrdn.practice_spring_backend.repository;

import com.codewithjmrdn.practice_spring_backend.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
