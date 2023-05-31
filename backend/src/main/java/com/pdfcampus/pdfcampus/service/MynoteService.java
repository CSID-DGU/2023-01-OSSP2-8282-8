package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.BookDto;
import com.pdfcampus.pdfcampus.dto.MynoteDto;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.MynoteRepository;
import com.pdfcampus.pdfcampus.repository.SaleRepository;
import com.pdfcampus.pdfcampus.service.ReadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class MynoteService {

    private final MynoteRepository mynoteRepository;
    private final SaleRepository saleRepository;
    private final ReadBookService readBookService;

    @Autowired
    public MynoteService(MynoteRepository mynoteRepository, SaleRepository saleRepository, ReadBookService readBookService) {
        this.mynoteRepository = mynoteRepository;
        this.saleRepository = saleRepository;
        this.readBookService = readBookService;
    }

    public List<MynoteDto> getMynoteByUserId(String userId) {
        List<Note> noteList = mynoteRepository.findByUserUid(Integer.valueOf(userId));

        if (noteList == null || noteList.isEmpty()) {
            return null;
        }

        List<MynoteDto> mynoteDtoList = new ArrayList<>();

        for (Note note : noteList) {
            boolean isSale = saleRepository.existsByNoteNid(note.getNid());

            MynoteDto mynoteDto = new MynoteDto();
            mynoteDto.setNoteId(note.getNid());
            mynoteDto.setNoteTitle(note.getNoteTitle());
            mynoteDto.setCreatedAt(note.getCreatedAt());
            mynoteDto.setModifiedAt(note.getModifiedAt());
            mynoteDto.setIsSale(isSale);

            String bookCoverUrl = null;
            try {
                bookCoverUrl = readBookService.getBookCoverUrl(String.valueOf(note.getBook().getBid())).toString();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            BookDto bookDto = new BookDto();
            bookDto.setAuthor(note.getBook().getAuthor());
            bookDto.setPublisher(note.getBook().getPublisher());
            bookDto.setPublicationYear(note.getBook().getPublicationYear());
            bookDto.setBookCover(bookCoverUrl);
            mynoteDto.setBookInfo(bookDto);

            mynoteDtoList.add(mynoteDto);
        }

        return mynoteDtoList;
    }
}
