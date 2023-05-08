package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.MylibDto;
import com.pdfcampus.pdfcampus.dto.MylibDto.MylibBookDto;
import com.pdfcampus.pdfcampus.dto.MylibDto.MylibNoteDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import com.pdfcampus.pdfcampus.repository.MylibRepository;
import com.pdfcampus.pdfcampus.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MylibService {
    private final MylibRepository mylibRepository;
    private final NoteRepository noteRepository;
    private final BookRepository bookRepository;

    public MylibService(MylibRepository mylibRepository, NoteRepository noteRepository, BookRepository bookRepository) {
        this.mylibRepository = mylibRepository;
        this.noteRepository = noteRepository;
        this.bookRepository = bookRepository;
    }

    public MylibDto getMylibData(String uid) {
        Integer uidInt = Integer.parseInt(uid);
        List<Mylib> mylibList = mylibRepository.findByUid(uidInt);

        List<MylibNoteDto> noteList = new ArrayList<>();
        List<MylibBookDto> bookList = new ArrayList<>();

        for (Mylib mylib : mylibList) {
            if (mylib.getNid() != null && mylib.getBid() != null) {
                Note note = noteRepository.findByNid(mylib.getNid()).stream()
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Note not found"));
               /* if (note == null) {
                    throw new IllegalArgumentException("Note not found");
                }*/
                noteList.add(new MylibNoteDto(note.getNid(), note.getNoteTitle(), note.getBook().getBookCover()));
            }
            else if (mylib.getNid() == null && mylib.getBid() != null) {
                Book book = bookRepository.findByBid(mylib.getBid()).stream()
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Note not found"));
               /* if (book == null) {
                    throw new IllegalArgumentException("Book not found");
                }*/
                bookList.add(new MylibBookDto(book.getBid(), book.getBookTitle(), book.getBookCover()));
            }
        }

        MylibDto mylibData = new MylibDto();
        mylibData.setUid(Integer.parseInt(uid));
        mylibData.setNoteList(noteList);
        mylibData.setBookList(bookList);
        return mylibData;
    }
}
