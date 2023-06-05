package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

    @Query("SELECT p FROM Page p WHERE p.bid = :bookId AND p.pageNumber IN :pageNumbers")
    List<Page> findByBookIdAndPageNumbers(@Param("bookId") Integer bookId, @Param("pageNumbers") List<Integer> pageNumbers);

    List<Page> findByBid(Integer bid);

    List<Page> findByNid(Integer nid);

    Optional<Page> findByBidAndPageNumber(int bid, int i);
}