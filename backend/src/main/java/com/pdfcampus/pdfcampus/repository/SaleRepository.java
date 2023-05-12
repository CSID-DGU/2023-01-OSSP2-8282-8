package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository  extends JpaRepository<Sale, Integer> {

    boolean existsByNoteNid(Integer nid);

    Sale findByNote(Note note);
}