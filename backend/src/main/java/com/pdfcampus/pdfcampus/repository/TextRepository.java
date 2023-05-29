package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Integer>  {
}
