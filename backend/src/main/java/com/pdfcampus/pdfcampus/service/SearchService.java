package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.DetailBookRepository;
import com.pdfcampus.pdfcampus.repository.DetailNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final DetailBookRepository detailBookRepository;
    private final DetailNoteRepository detailNoteRepository;

    @Autowired
    public SearchService(DetailBookRepository detailBookRepository,
                         DetailNoteRepository detailNoteRepository) {
        this.detailBookRepository = detailBookRepository;
        this.detailNoteRepository = detailNoteRepository;
    }

    /*public List<DetailBookDto> searchBooks(String keyword) {
        List<Book> detailBookList = detailBookRepository.findByBookTitleContainingIgnoreCase(keyword);
        return detailBookList.stream()
                .map(detailBook -> new DetailBookDto(
                        detailBook.getBookTitle(),
                        detailBook.getAuthor(),
                        detailBook.getPublisher(),
                        detailBook.getPublicationYear(),
                        detailBook.getBookCover()
                       // detailBook.getIsStored()))
                .collect(Collectors.toList());
    }

    public List<DetailNoteDto> searchNotes(String keyword) {
        List<Note> detailNoteList = detailNoteRepository.findByNoteTitleContainingIgnoreCase(keyword);
        return detailNoteList.stream()
                .map(detailNote -> new DetailNoteDto(
                        detailNote.getNoteTitle(),
                        detailNote.getAuthor(),
                        detailNote.getCreatedAt(),
                        detailNote.getModifiedAt();
                       // detailNote.getPrice(),
                       // detailNote.getIsBought(),
                        //detailNote.getBookInfo()))
                .collect(Collectors.toList());
    }*/
}
