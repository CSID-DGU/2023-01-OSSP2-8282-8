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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {
    private final DetailBookRepository detailBookRepository;
    private final DetailNoteRepository detailNoteRepository;
    private final SaleRepository saleRepository;
    private final MylibService mylibService;
    public DetailService(DetailBookRepository detailBookRepository, DetailNoteRepository detailNoteRepository, MylibService mylibService, SaleRepository saleRepository) {
        this.detailBookRepository = detailBookRepository;
        this.detailNoteRepository = detailNoteRepository;
        this.mylibService = mylibService;
        this.saleRepository = saleRepository;
    }

    public DetailBookDto getBookData(String bid) { // 데이터베이스에서 책에 대한 데이터를 가져오도록 레포지토리 호출
        Integer bidInt = Integer.parseInt(bid);
        Book book = detailBookRepository.findByBid(bidInt).orElse(null); // bookId를 통해 레포지토리를 호출하여 엔티티 받기
        return new DetailBookDto( //form 형태에 맞는 dto 반환
                book.getBid(),
                book.getBookTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublicationYear(),
                book.getBookCover(),
                false);
    }

    public DetailNoteDto getNoteData(String nid) { // 데이터베이스에서 필기에 대한 데이터를 가져오도록 =1&bookId=2레포지토리 호출
        Integer nidInt = Integer.parseInt(nid);
        Note note = detailNoteRepository.findByNid(nidInt).orElse(null); // noteId를 통해 레포지토리를 호출하여 엔티티 받기

        Sale sale = saleRepository.findByNote(note).orElse(null);

        return new DetailNoteDto(
                note.getNid(),
                note.getNoteTitle(),
                note.getUser().getUsername(),
                note.getCreatedAt(),
                note.getModifiedAt(),
                sale != null ? sale.getPrice().toString() : null,
                sale != null ? true : false,
                note.getBook().getAuthor(),
                note.getUser().getUid(),
                note.getBook().getPublisher(),
                note.getBook().getPublicationYear(),
                note.getBook().getBookCover());
    }


    public boolean isStored(String uid, String bid){
        Integer bidInt = Integer.parseInt(bid);
        List<MylibBookDto> userBookList = mylibService.getMylibBookData(uid);
        for (MylibBookDto mylibBookDto : userBookList){
            if(mylibBookDto.getBookId() == bidInt) return true; //만약 나의 서재의 필기 리스트에서 일치하는 nid가 있다면 true
        }

        return false; // 하나도 없으면 false
    }

    /*public boolean isBought(String uid, String nid){
        Integer nidInt = Integer.parseInt(nid);
        List<MylibNoteDto> userNoteList = mylibService.getMylibNoteData(uid);
        for (MylibNoteDto mylibNoteDto : userNoteList){
            if(mylibNoteDto.getNoteId() == nidInt) return true; //만약 나의 서재의 대여 도서 리스트에서 일치하는 bid가 있다면 true
        }

        return false; // 하나도 없으면 false
    }*/
}
