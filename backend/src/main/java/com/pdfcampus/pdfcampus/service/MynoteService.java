package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.BookDto;
import com.pdfcampus.pdfcampus.dto.MynoteDto;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.MylibRepository;
import com.pdfcampus.pdfcampus.repository.MynoteRepository;
import com.pdfcampus.pdfcampus.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MynoteService {

    private final MynoteRepository mynoteRepository;
    private final MylibRepository mylibRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public MynoteService(MynoteRepository mynoteRepository, SaleRepository saleRepository, MylibRepository mylibRepository) {
        this.mynoteRepository = mynoteRepository;
        this.saleRepository = saleRepository;
        this.mylibRepository = mylibRepository;
    }

    public boolean deleteNote(String uid, String nid) {
        Integer userId = Integer.parseInt(uid);
        Integer noteId = Integer.parseInt(nid);
        Optional<Mylib> dataOptional = mylibRepository.findByUidAndNid(userId, noteId);

        if (dataOptional.isPresent()) {
            mynoteRepository.deleteByNid(noteId);
            return true;
        }

        return false;
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

            BookDto bookDto = new BookDto();
            bookDto.setAuthor(note.getBook().getAuthor());
            bookDto.setPublisher(note.getBook().getPublisher());
            bookDto.setPublicationYear(note.getBook().getPublicationYear());
            bookDto.setBookCover(note.getBook().getBookCover());
            mynoteDto.setBookInfo(bookDto);

            mynoteDtoList.add(mynoteDto);
        }

        return mynoteDtoList;
    }
}
