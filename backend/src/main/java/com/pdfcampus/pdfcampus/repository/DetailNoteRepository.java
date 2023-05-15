package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DetailNoteRepository extends JpaRepository<Note, Integer> {
    Optional<Note> findByNid(Integer noteId);
}