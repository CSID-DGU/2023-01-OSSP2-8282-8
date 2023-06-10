package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignupRepository extends JpaRepository<User, Integer> {
    boolean existsByUserId(String userId);
}
