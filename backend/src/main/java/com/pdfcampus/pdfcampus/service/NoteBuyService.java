package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.DetailNoteRepository;
import com.pdfcampus.pdfcampus.repository.MylibRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class NoteBuyService {
    private final DetailNoteRepository detailNoteRepository;
    private final MylibRepository mylibRepository;

    public NoteBuyService(DetailNoteRepository detailNoteRepository, MylibRepository mylibRepository){
        this.detailNoteRepository = detailNoteRepository;
        this.mylibRepository = mylibRepository;
    }

    @Transactional
    public Note buyNote(Integer userId, Integer noteId){
        // 중복체크 -> 혹시 몰라서 작성
        Optional<Mylib> mylibOptional = mylibRepository.findByUidAndNid(userId, noteId);
        if (mylibOptional.isPresent()) {
            throw new IllegalArgumentException("This note already exists in the user's library.");
        }

        Note note = detailNoteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note id."));
        // mylib add
        Book book = note.getBook();
        Mylib mylib = new Mylib(userId, note, book);
        mylibRepository.save(mylib);
        return note;
    }
}
