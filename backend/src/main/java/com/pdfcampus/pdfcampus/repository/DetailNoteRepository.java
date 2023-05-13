package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailNoteRepository extends JpaRepository<Note, Integer> {
    Note findByNid(Integer noteId);

    List<Note> findByNoteTitleContainingIgnoreCase(String keyword);
}