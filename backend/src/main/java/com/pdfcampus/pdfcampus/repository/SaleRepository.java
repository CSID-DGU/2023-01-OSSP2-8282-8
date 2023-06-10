package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository  extends JpaRepository<Sale, Integer> {

    List<Sale> findTop10ByOrderByNoteDesc();
    List<Sale> findByNote_NoteTitleContainingIgnoreCase(String keyword);

    boolean existsByNoteNid(Integer nid);
    Optional<Sale> findByNote(Note note);
}
