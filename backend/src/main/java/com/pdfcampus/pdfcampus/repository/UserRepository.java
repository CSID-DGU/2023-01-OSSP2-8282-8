package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Object findByUid(Integer integer);
}

