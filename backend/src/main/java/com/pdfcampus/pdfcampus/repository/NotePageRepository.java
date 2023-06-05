package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.NotePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotePageRepository extends JpaRepository<NotePage, Integer> {
    List<NotePage> findByNid(Integer nid);
}