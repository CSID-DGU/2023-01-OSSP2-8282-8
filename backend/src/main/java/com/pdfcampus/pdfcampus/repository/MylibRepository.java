package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.Mylib;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MylibRepository extends JpaRepository<Mylib, Integer> {
    List<Mylib> findByUid(Integer uid);

    Optional<Mylib> findByUidAndNid(Integer userId, Integer noteId);

    boolean existsByUidAndNid(Integer uid, Integer nid);

    void deleteByUidAndNid(Integer userId, Integer noteId);
}
