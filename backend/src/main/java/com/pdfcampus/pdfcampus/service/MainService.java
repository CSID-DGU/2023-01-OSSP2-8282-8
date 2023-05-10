package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.MylibBookDto;
import com.pdfcampus.pdfcampus.dto.MylibNoteDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import com.pdfcampus.pdfcampus.repository.MylibRepository;
import com.pdfcampus.pdfcampus.repository.NoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class MainService {
    private final MylibRepository mylibRepository;
    private final NoteRepository noteRepository;
    private final BookRepository bookRepository;

    public MainService(MylibRepository mylibRepository, NoteRepository noteRepository, BookRepository bookRepository) {
        this.mylibRepository = mylibRepository;
        this.noteRepository = noteRepository;
        this.bookRepository = bookRepository;
    }

    public List<MylibNoteDto> getMainNoteData() {
        List<Note> notes = noteRepository.findTop10ByOrderByNidDesc();
        return notes.stream().map(note -> new MylibNoteDto(note.getNid(), note.getNoteTitle(), note.getBook().getBookCover())).collect(Collectors.toList());
    }

    public List<MylibBookDto> getMainBookData() {
        List<Book> books = bookRepository.findTop10ByOrderByBidDesc();
        return books.stream().map(book -> new MylibBookDto(book.getBid(), book.getBookTitle(), book.getBookCover())).collect(Collectors.toList());
    }
}
