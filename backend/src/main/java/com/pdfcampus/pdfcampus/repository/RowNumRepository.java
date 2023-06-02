package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.RowNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RowNumRepository extends JpaRepository<RowNum, Integer> {

    Optional<RowNum> findByPidAndRowNumber(Integer pid, int rowNumber);
}
