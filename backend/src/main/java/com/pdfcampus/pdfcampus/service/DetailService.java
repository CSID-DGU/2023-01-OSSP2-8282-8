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
        Book book = detailBookRepository.findByBid(bidInt); // bookId를 통해 레포지토리를 호출하여 엔티티 받기
        return new DetailBookDto( //form 형태에 맞는 dto 반환
                book.getBid(),
                book.getBookTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublicationYear(),
                book.getBookCover(),
                false);
    }

    public DetailNoteDto getNoteData(String nid) { // 데이터베이스에서 필기에 대한 데이터를 가져오도록 레포지토리 호출
        Integer nidInt = Integer.parseInt(nid);
        Note note = detailNoteRepository.findByNid(nidInt); // noteId를 통해 레포지토리를 호출하여 엔티티 받기
        Sale sale = saleRepository.findByNote(note);
        return new DetailNoteDto( //form 형태에 맞는 dto 반환
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
                note.getBook().getBookCover());
    }


    public boolean isStored(String uid, String nid){
        Integer uidInt = Integer.parseInt(uid);
        Integer nidInt = Integer.parseInt(nid);
        List<MylibNoteDto> userNoteList = mylibService.getMylibNoteData(uid);
        for (MylibNoteDto mylibNoteDto : userNoteList){
            if(mylibNoteDto.getNoteId() == nidInt) return true;
        }

        return false;
    }

    public boolean isBought(String uid, String bid){
        Integer uidInt = Integer.parseInt(uid);
        Integer bidInt = Integer.parseInt(bid);
        List<MylibBookDto> userBookList = mylibService.getMylibBookData(uid);
        for (MylibBookDto mylibBookDto : userBookList){
            if(mylibBookDto.getBookId() == bidInt) return true;
        }

        return false;
    }
}
