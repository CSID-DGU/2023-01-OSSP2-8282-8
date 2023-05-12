package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailBookRepository extends JpaRepository<Book, Integer> {
    Book findByBid(Integer bookId);

    List<Book> findByBookTitleContainingIgnoreCase(String keyword);
}
