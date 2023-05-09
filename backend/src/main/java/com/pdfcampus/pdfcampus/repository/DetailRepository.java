package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Book, Integer> {
    Book findByBid(Integer bookId);
}
