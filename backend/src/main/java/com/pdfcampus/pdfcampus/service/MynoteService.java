package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.BookDto;
import com.pdfcampus.pdfcampus.dto.MynoteAssignDto;
import com.pdfcampus.pdfcampus.dto.MynoteDto;
import com.pdfcampus.pdfcampus.dto.SignupDto;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.entity.Sale;
import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.amazonaws.services.s3.model.DeleteObjectRequest;

@Service
@Transactional
public class MynoteService {

    private final MynoteRepository mynoteRepository;
    private final MylibRepository mylibRepository;
    private final SaleRepository saleRepository;
    private final ReadBookService readBookService;
    private final DetailNoteRepository detailNoteRepository;
    private final NotePageRepository notePageRepository;

    @Autowired
    public MynoteService(NotePageRepository notePageRepository, MynoteRepository mynoteRepository, SaleRepository saleRepository, MylibRepository mylibRepository, ReadBookService readBookService, DetailNoteRepository detailNoteRepository) {
        this.mynoteRepository = mynoteRepository;
        this.saleRepository = saleRepository;
        this.mylibRepository = mylibRepository;
        this.readBookService = readBookService;
        this.detailNoteRepository = detailNoteRepository;
        this.notePageRepository = notePageRepository;
    }

    public boolean deleteNote(String uid, String nid) {
        Integer userId = Integer.parseInt(uid);
        Integer noteId = Integer.parseInt(nid);
        boolean isNotePresent = mynoteRepository.existsByNid(noteId);
        boolean isMylibPresent = mylibRepository.existsByUidAndNid(userId, noteId);

        if (isMylibPresent) { // 나의 서재에서도 삭제
            mylibRepository.deleteByUidAndNid(userId, noteId);
        }

        if (isNotePresent) { // Note db에서 데이터를 찾고 있으면 지워버림 없으면 에러 반환
            mynoteRepository.deleteByNid(noteId);
        }
        else{
            throw new EntityNotFoundException("Note not found in mynoteRepository with id " + noteId);
        }
        /*if (notePageRepository.existsByNid(noteId)) {
            notePageRepository.deleteByNid(noteId);
            return true;
        }
        else{
            throw new EntityNotFoundException("Note not found in notePageRepository with id " + noteId);
        }*/
        if (isNotePresent)
            return true;

        return false;
    }

    public boolean existsByNid(String noteId) {
        Integer nidInt = Integer.parseInt(noteId);

        return saleRepository.existsByNoteNid(nidInt);
    }

    public Sale assignNote(MynoteAssignDto mynoteAssignDto) {
        Integer nidInt = Integer.parseInt(mynoteAssignDto.getNoteId());
        Note note = detailNoteRepository.findByNid(nidInt)
                .orElseThrow(() -> new EntityNotFoundException("note not found with id " + mynoteAssignDto.getNoteId()));

        Sale sale = mynoteAssignDto.toEntity(note, mynoteAssignDto.getPrice());

        return saleRepository.save(sale);
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
