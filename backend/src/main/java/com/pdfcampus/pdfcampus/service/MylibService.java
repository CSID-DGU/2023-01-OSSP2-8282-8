package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.MylibDto;
import com.pdfcampus.pdfcampus.dto.MylibBookDto;
import com.pdfcampus.pdfcampus.dto.MylibNoteDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Mylib;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import com.pdfcampus.pdfcampus.repository.MylibRepository;
import com.pdfcampus.pdfcampus.repository.NoteRepository;

import java.util.ArrayList;
import java.util.Arrays;
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
        Mylib[] mylibArray = mylibRepository.findByUid(uidInt).toArray(new Mylib[0]);
        for (Mylib mylib : mylibArray) {
            System.out.println("mid: " + mylib.getMid());
            System.out.println("uid: " + mylib.getUid());
            System.out.println("nid: " + mylib.getNid());
            System.out.println("bid: " + mylib.getBid());
        }

        MylibDto mylibData = new MylibDto();
        mylibData.setUid(Integer.parseInt(uid));

        MylibNoteDto[] noteArray = Arrays.stream(mylibArray)
                .filter(mylib -> mylib.getNid() != null && mylib.getBid() != null)
                .map(mylib -> {
                    Note note = noteRepository.findByNid(mylib.getNid()).get(0);
                    Book book = bookRepository.findByBid(mylib.getBid()).get(0);
                    return new MylibNoteDto(note.getNid(), note.getNoteTitle(), note.getBook().getBookCover());
                })
                .toArray(MylibNoteDto[]::new);
        for (MylibNoteDto note : noteArray) {
            System.out.println("Note Id: " + note.getNoteId());
            System.out.println("Note Title: " + note.getNoteTitle());
            System.out.println("Book Cover: " + note.getBookCover());
        }


        MylibBookDto[] bookArray = Arrays.stream(mylibArray)
                .filter(mylib -> mylib.getNid() == null && mylib.getBid() != null)
                .map(mylib -> {
                    Book book = bookRepository.findByBid(mylib.getBid()).get(0);
                    return new MylibBookDto(book.getBid(), book.getBookTitle(), book.getBookCover());
                })
                .toArray(MylibBookDto[]::new);

        mylibData.setNoteList(Arrays.asList(noteArray));
        mylibData.setBookList(Arrays.asList(bookArray));
        return mylibData;

    }
    public List<MylibNoteDto> getMylibNoteData(String uid) {
        Integer uidInt = Integer.parseInt(uid);
        List<Mylib> mylibList = mylibRepository.findByUid(uidInt);
        List<MylibNoteDto> noteList = new ArrayList<>();
        for (Mylib mylib : mylibList) {
            if (mylib.getNid() != null && mylib.getBid() != null) {
                Note note = noteRepository.findByNid(mylib.getNid()).get(0);
                noteList.add(new MylibNoteDto(note.getNid(), note.getNoteTitle(), note.getBook().getBookCover()));
            }
        }
        return noteList;
    }

    public List<MylibBookDto> getMylibBookData(String uid) {
        Integer uidInt = Integer.parseInt(uid);
        List<Mylib> mylibList = mylibRepository.findByUid(uidInt);
        List<MylibBookDto> bookList = new ArrayList<>();
        for (Mylib mylib : mylibList) {
            if (mylib.getNid() == null && mylib.getBid() != null) {
                Book book = bookRepository.findByBid(mylib.getBid()).get(0);
                bookList.add(new MylibBookDto(book.getBid(), book.getBookTitle(), book.getBookCover()));
            }
        }
        return bookList;
    }
}
