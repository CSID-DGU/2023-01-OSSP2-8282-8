package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageRepository extends JpaRepository<User, Integer> {
    User findByUid(Integer userId);
}