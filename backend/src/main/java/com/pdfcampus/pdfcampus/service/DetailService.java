package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.DetailBookRepository;
import com.pdfcampus.pdfcampus.repository.DetailNoteRepository;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    private final DetailBookRepository detailBookRepository;
    private final DetailNoteRepository detailNoteRepository;
    public DetailService(DetailBookRepository detailBookRepository, DetailNoteRepository detailNoteRepository) {
        this.detailBookRepository = detailBookRepository;
        this.detailNoteRepository = detailNoteRepository;
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
                true);
    }

    public DetailNoteDto getNoteData(String nid) { // 데이터베이스에서 필기에 대한 데이터를 가져오도록 레포지토리 호출
        Integer nidInt = Integer.parseInt(nid);
        Note note = detailNoteRepository.findByNid(nidInt); // noteId를 통해 레포지토리를 호출하여 엔티티 받기
        return new DetailNoteDto( //form 형태에 맞는 dto 반환
                note.getNid(),
                note.getNoteTitle(),
                note.getUser().getUsername(),
                note.getCreatedAt(),
                note.getModifiedAt(),
                "1",
                true,
                note.getBook().getAuthor(),
                note.getUser().getUid(),
                note.getBook().getPublisher(),
                note.getBook().getPublicationYear(),
                note.getBook().getBookCover());
    }
}
