package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.BookAddDto;
import com.pdfcampus.pdfcampus.dto.BookDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import com.pdfcampus.pdfcampus.repository.DetailBookRepository;
import com.pdfcampus.pdfcampus.repository.MylibRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAddService {
    private final DetailBookRepository detailBookRepository;
    private final DetailService detailService;
    private final MylibRepository mylibRepository;

    public BookAddService(DetailBookRepository detailBookRepository, DetailService detailService, MylibRepository mylibRepository){
        this.detailBookRepository = detailBookRepository;
        this.detailService = detailService;
        this.mylibRepository = mylibRepository;
    }
    public Mylib addBook(BookAddDto addDto) {
        Integer uidInt = Integer.parseInt(addDto.getUid());

        Book note = detailBookRepository.findById(addDto.getBid())
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id."));

        return mylibRepository.save(new Mylib(uidInt, note));
    }

    public boolean isDuplicated(BookAddDto addDto){
        // uid에 해당하는 mylib에서 bid에 해당하는 book이 store 되어있는지 확인. stroe 되어있다면 해당 bid는 중복되는 것임
        return detailService.isStored(addDto.getUid(), Integer.toString(addDto.getBid()));
    }
}
