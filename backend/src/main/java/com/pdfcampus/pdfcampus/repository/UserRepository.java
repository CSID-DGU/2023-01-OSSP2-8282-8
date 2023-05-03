package com.pdfcampus.pdfcampus.repository;


import com.pdfcampus.pdfcampus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository  extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
}
