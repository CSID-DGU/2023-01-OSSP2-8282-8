package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByBid(Integer bid);

    List<Book> findByBookTitle(String bookTitle);

    List<Book> findTop10ByOrderByBidDesc();

    List<Book> findByBookTitleContainingIgnoreCase(String keyword);
}
