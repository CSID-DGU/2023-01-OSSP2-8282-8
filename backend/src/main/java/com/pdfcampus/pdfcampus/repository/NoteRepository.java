package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByNid(Integer nid);

    List<Note> findTop10ByOrderByNidDesc();

    List<Note> findByNoteTitleContainingIgnoreCase(String keyword);
    Optional<Note> findByBook(Integer bookId);

}
