package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailBookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByBid(Integer bookId);
}
