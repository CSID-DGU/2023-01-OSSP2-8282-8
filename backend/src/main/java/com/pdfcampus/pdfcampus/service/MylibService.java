package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.MylibRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MylibService {
    private final MylibRepository mylibRepository;

    public List<Mylib> getMylibList(int uid) {
        return mylibRepository.findAll();
    }

    public List<Book> getUniqueBookList(List<Mylib> mylibList) {
        return mylibList.stream()
                .map(Mylib::getBook)
                .filter(book -> book != null)
                .distinct()
                .collect(Collectors.toList());
    }
}
