package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

    @Query("SELECT p FROM Page p WHERE p.bid = :bookId AND p.pageNumber IN :pageNumbers")
    List<Page> findByBookIdAndPageNumbers(@Param("bookId") Integer bookId, @Param("pageNumbers") List<Integer> pageNumbers);

}