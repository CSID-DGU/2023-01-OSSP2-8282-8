package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer>{
    LoginEntity findByUserid(String userid);
    LoginEntity findByUseridAndPassword(String userid, String password);

}