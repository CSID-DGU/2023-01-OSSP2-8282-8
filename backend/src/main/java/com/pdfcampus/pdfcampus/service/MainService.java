package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.MylibBookDto;
import com.pdfcampus.pdfcampus.dto.MylibNoteDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.entity.Sale;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import com.pdfcampus.pdfcampus.repository.MylibRepository;
import com.pdfcampus.pdfcampus.repository.NoteRepository;
import com.pdfcampus.pdfcampus.repository.SaleRepository;
import com.pdfcampus.pdfcampus.service.ReadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
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
    private final SaleRepository saleRepository;
    private final ReadBookService readBookService;

    @Autowired
    public MainService(MylibRepository mylibRepository, NoteRepository noteRepository, BookRepository bookRepository, SaleRepository saleRepository, ReadBookService readBookService) {
        this.mylibRepository = mylibRepository;
        this.noteRepository = noteRepository;
        this.bookRepository = bookRepository;
        this.saleRepository = saleRepository;
        this.readBookService = readBookService;
    }

    public List<MylibNoteDto> getMainNoteData() {

        List<Sale> sales = saleRepository.findTop10ByOrderByNoteDesc();
        List<Note> notes = new ArrayList<>();

        for(Sale sale : sales) {
            Note note = sale.getNote();
            if(note != null) {
                notes.add(note);
            }
        }

        return notes.stream()
                .map(note -> {
                    String bookCoverUrl = null;
                    try {
                        bookCoverUrl = readBookService.getBookCoverUrl(note.getNid().toString()).toString();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    return new MylibNoteDto(note.getNid(), note.getNoteTitle(), bookCoverUrl);
                })
                .collect(Collectors.toList());
    }

    public List<MylibBookDto> getMainBookData() {
        List<Book> books = bookRepository.findTop10ByOrderByBidDesc();
        return books.stream()
                .map(book -> {
                    String bookCoverUrl = null;
                    try {
                        bookCoverUrl = readBookService.getBookCoverUrl(String.valueOf(book.getBid())).toString();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    return new MylibBookDto(book.getBid(), book.getBookTitle(), bookCoverUrl);
                })
                .collect(Collectors.toList());
    }
}
