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
import com.pdfcampus.pdfcampus.service.ReadBookService;

import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MylibService {
    private final MylibRepository mylibRepository;
    private final NoteRepository noteRepository;
    private final BookRepository bookRepository;
    private final ReadBookService readBookService;

    public MylibService(MylibRepository mylibRepository, NoteRepository noteRepository, BookRepository bookRepository, ReadBookService readBookService) {
        this.mylibRepository = mylibRepository;
        this.noteRepository = noteRepository;
        this.bookRepository = bookRepository;
        this.readBookService = readBookService;
    }

    public MylibDto getMylibData(String uid) {
        Integer uidInt = Integer.parseInt(uid);
        Mylib[] mylibArray = mylibRepository.findByUid(uidInt).toArray(new Mylib[0]);
        Arrays.sort(mylibArray, Comparator.comparing(Mylib::getMid).reversed());

        MylibDto mylibData = new MylibDto();
        mylibData.setUid(Integer.parseInt(uid));

        MylibNoteDto[] noteArray = Arrays.stream(mylibArray)
                .filter(mylib -> mylib.getNid() != null && mylib.getBid() != null)
                .map(mylib -> {
                    Note note = noteRepository.findByNid(mylib.getNid()).get(0);
                    Book book = bookRepository.findByBid(mylib.getBid()).get(0);
                    String bookCoverUrl = null;
                    try {
                        bookCoverUrl = readBookService.getBookCoverUrl(mylib.getBid().toString()).toString();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    return new MylibNoteDto(note.getNid(), note.getNoteTitle(), bookCoverUrl);
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
                    String bookCoverUrl = null;
                    try {
                        bookCoverUrl = readBookService.getBookCoverUrl(mylib.getBid().toString()).toString();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    return new MylibBookDto(book.getBid(), book.getBookTitle(), bookCoverUrl);
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
                String bookCoverUrl = null;
                try {
                    bookCoverUrl = readBookService.getBookCoverUrl(mylib.getBid().toString()).toString();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                noteList.add(new MylibNoteDto(note.getNid(), note.getNoteTitle(), bookCoverUrl));
            }
        }
        Collections.sort(noteList, Comparator.comparing(MylibNoteDto::getNoteId).reversed());
        return noteList;
    }

    public List<MylibBookDto> getMylibBookData(String uid) {
        Integer uidInt = Integer.parseInt(uid);
        List<Mylib> mylibList = mylibRepository.findByUid(uidInt);
        List<MylibBookDto> bookList = new ArrayList<>();
        for (Mylib mylib : mylibList) {
            if (mylib.getNid() == null && mylib.getBid() != null) {
                Book book = bookRepository.findByBid(mylib.getBid()).get(0);
                String bookCoverUrl = null;
                try {
                    bookCoverUrl = readBookService.getBookCoverUrl(mylib.getBid().toString()).toString();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                bookList.add(new MylibBookDto(book.getBid(), book.getBookTitle(), bookCoverUrl));
            }
        }
        Collections.sort(bookList, Comparator.comparing(MylibBookDto::getBookId).reversed());
        return bookList;
    }
}
