package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignupRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String userId);

    boolean existsByUserId(String userId);
}
