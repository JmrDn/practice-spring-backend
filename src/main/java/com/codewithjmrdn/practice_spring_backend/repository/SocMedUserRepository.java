package com.codewithjmrdn.practice_spring_backend.repository;

import com.codewithjmrdn.practice_spring_backend.model.SocMedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocMedUserRepository extends JpaRepository<SocMedUser, Integer> {
}
