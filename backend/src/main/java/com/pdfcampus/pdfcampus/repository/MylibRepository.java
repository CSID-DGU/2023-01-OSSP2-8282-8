package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Mylib;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MylibRepository extends JpaRepository<Mylib, Integer> {
    List<Mylib> findByUid(Integer uid);
}
