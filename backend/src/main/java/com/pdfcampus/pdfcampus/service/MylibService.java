package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.repository.MylibRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MylibService {
    private final MylibRepository mylibRepository;

    public List<Mylib> getMylibList() {
        return mylibRepository.findAll();
    }
}
