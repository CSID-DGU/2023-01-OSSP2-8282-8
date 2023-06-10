package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.NotePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotePageRepository extends JpaRepository<NotePage, Integer> {

    List<NotePage> findByNid(Integer nid);

    void deleteByNid(Integer nid);

    boolean existsByNid(Integer nid);
}
