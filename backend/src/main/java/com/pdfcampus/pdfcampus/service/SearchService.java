package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
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
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final DetailBookRepository detailBookRepository;
    private final DetailNoteRepository detailNoteRepository;
    private final SaleRepository saleRepository;
    private final MylibService mylibService;
    private final DetailService detailService;
    private final ReadBookService readBookService;


    @Autowired
    public SearchService(DetailBookRepository detailBookRepository, DetailNoteRepository detailNoteRepository, SaleRepository saleRepository, DetailService detailService, MylibService mylibService, ReadBookService readBookService) {
        this.detailBookRepository = detailBookRepository;
        this.detailNoteRepository = detailNoteRepository;
        this.saleRepository = saleRepository;
        this.mylibService = mylibService;
        this.detailService = detailService;
        this.readBookService = readBookService;
    }

    public List<DetailBookDto> searchBooks(String keyword, String userId) {
        List<Book> books = detailBookRepository.findByBookTitleContainingIgnoreCase(keyword);
        return books.stream()
                .map(book -> {
                    String bookCoverUrl;
                    try {
                        bookCoverUrl = readBookService.getBookCoverUrl(String.valueOf(book.getBid())).toString();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    DetailBookDto detailBookDto = new DetailBookDto(
                            book.getBid(),
                            book.getBookTitle(),
                            book.getAuthor(),
                            book.getPublisher(),
                            book.getPublicationYear(),
                            bookCoverUrl,
                            detailService.isStored(userId, String.valueOf(book.getBid()))
                    );
                    return detailBookDto;
                })
                .collect(Collectors.toList());
    }


    public List<DetailNoteDto> searchNotes(String keyword) {
        List<Sale> sales = saleRepository.findByNote_NoteTitleContainingIgnoreCase(keyword);
        return sales.stream()
                .map(sale -> {
                    Note note = sale.getNote();
                    String bookCoverUrl;
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
                            true,
                            note.getBook().getAuthor(),
                            note.getUser().getUid(),
                            note.getBook().getPublisher(),
                            note.getBook().getPublicationYear(),
                            bookCoverUrl);
                })
                .collect(Collectors.toList());
    }
}