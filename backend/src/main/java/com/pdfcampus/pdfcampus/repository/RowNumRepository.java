package com.pdfcampus.pdfcampus.repository;

import com.pdfcampus.pdfcampus.entity.RowNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RowNumRepository extends JpaRepository<RowNum, Integer> {

    Optional<RowNum> findByPidAndRowNumber(Integer pid, int rowNumber);

    RowNum findFirstByPidAndRowYGreaterThanEqualOrderByRowYAsc(Integer pid, float y);
    RowNum findFirstByPidAndRowYLessThanOrderByRowYDesc(Integer pid, float y);

    RowNum findFirstByPidAndRowYGreaterThanOrderByRowYAsc(Integer pid, float v);

    RowNum findFirstByPidAndRowYLessThanEqualOrderByRowYDesc(Integer pid, float v);

    List<RowNum> findByPid(Integer pid);
}
