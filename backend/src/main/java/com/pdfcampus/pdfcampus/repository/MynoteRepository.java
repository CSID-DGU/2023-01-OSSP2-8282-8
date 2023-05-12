package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MynoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByUserUid(Integer uid);
}
