package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer>{
    User findByUserId(String userid);
    User findByUserIdAndPassword(String userid, String password);

}