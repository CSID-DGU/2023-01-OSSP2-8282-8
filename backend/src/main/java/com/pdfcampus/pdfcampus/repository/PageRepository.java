package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {
}
