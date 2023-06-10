package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.dto.MylibBookDto;
import com.pdfcampus.pdfcampus.dto.MylibNoteDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.entity.Sale;
import com.pdfcampus.pdfcampus.repository.DetailBookRepository;
import com.pdfcampus.pdfcampus.repository.DetailNoteRepository;
import com.pdfcampus.pdfcampus.repository.SaleRepository;
import com.pdfcampus.pdfcampus.service.ReadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Service
public class DetailService {
    private final DetailBookRepository detailBookRepository;
    private final DetailNoteRepository detailNoteRepository;
    private final SaleRepository saleRepository;
    private final MylibService mylibService;
    @Autowired
    private final ReadBookService readBookService;

    public DetailService(DetailBookRepository detailBookRepository, DetailNoteRepository detailNoteRepository, MylibService mylibService, SaleRepository saleRepository, ReadBookService readBookService) {
        this.detailBookRepository = detailBookRepository;
        this.detailNoteRepository = detailNoteRepository;
        this.mylibService = mylibService;
        this.saleRepository = saleRepository;
        this.readBookService = readBookService;
    }

    public DetailBookDto getBookData(String bid) { // 데이터베이스에서 책에 대한 데이터를 가져오도록 레포지토리 호출
        Integer bidInt = Integer.parseInt(bid);
        Book book = detailBookRepository.findByBid(bidInt).orElseThrow(() -> new NullPointerException("Book not found"));

        String bookCoverUrl = null;
        try {
            bookCoverUrl = readBookService.getBookCoverUrl(String.valueOf(book.getBid())).toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return new DetailBookDto( //form 형태에 맞는 dto 반환
                book.getBid(),
                book.getBookTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublicationYear(),
                bookCoverUrl,
                false);
    }

    public DetailNoteDto getNoteData(String nid) { // 데이터베이스에서 필기에 대한 데이터를 가져오도록 =1&bookId=2레포지토리 호출
        Integer nidInt = Integer.parseInt(nid);
        Note note = detailNoteRepository.findByNid(nidInt).orElseThrow(() -> new NullPointerException("Note not found"));
        Sale sale = saleRepository.findByNote(note).orElse(null);

        String bookCoverUrl = null;
        try {
            bookCoverUrl = readBookService.getBookCoverUrl(String.valueOf(note.getBook().getBid())).toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return new DetailNoteDto(
                note.getNid(),
                note.getNoteTitle(),
                note.getUser().getUsername(),
                note.getCreatedAt(),
                note.getModifiedAt(),
                sale.getPrice().toString(),
                false,
                note.getBook().getAuthor(),
                note.getUser().getUid(),
                note.getBook().getPublisher(),
                note.getBook().getPublicationYear(),
                bookCoverUrl);
    }


    public boolean isStored(String uid, String bid){
        Integer bidInt = Integer.parseInt(bid);
        List<MylibBookDto> userBookList = mylibService.getMylibBookData(uid);
        for (MylibBookDto mylibBookDto : userBookList){
            if(mylibBookDto.getBookId() == bidInt) return true; //만약 나의 서재의 도서 리스트에서 일치하는 bid가 있다면 true
        }

        return false; // 하나도 없으면 false
    }

    public boolean isBought(String uid, String nid){
        Integer nidInt = Integer.parseInt(nid);
        List<MylibNoteDto> userNoteList = mylibService.getMylibNoteData(uid);
        for (MylibNoteDto mylibNoteDto : userNoteList){
            if(mylibNoteDto.getNoteId() == nidInt) return true;
        }

        return false; // 하나도 없으면 false
    }
}
