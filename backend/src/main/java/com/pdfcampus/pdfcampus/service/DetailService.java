package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.DetailRepository;

public class DetailService {
    private final DetailRepository detailRepository;
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public DetailBookDto getBookData(String bid) { // 데이터베이스에서 책에 대한 데이터를 가져오도록 레포지토리 호출
        Integer bidInt = Integer.parseInt(bid);
        Book book = detailRepository.findByBid(bidInt);
        return new DetailBookDto(book.getBid(), book.getBookTitle(), book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getBookCover(), true);
    }
}
