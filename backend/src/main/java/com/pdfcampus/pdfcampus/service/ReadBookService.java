package com.pdfcampus.pdfcampus.service;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadBookService {
    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    private NoteRepository noteRepository;

    public Book getBookById(String bookId) {
        String bookBucket = "book-bucket"; // 책 이미지가 저장된 버킷
        byte[] bookImage = amazonS3ClientService.downloadFile(bookBucket, bookId);

        Book book = new Book();
        book.setBid(Integer.parseInt(bookId));
        //book.setBookImage(bookImage);

        Optional<Note> noteOptional = noteRepository.findByBookId(bookId);
        if(noteOptional.isPresent()){
            String notesBucket = "notes-bucket"; // 노트 이미지가 저장된 버킷
            byte[] noteImage = amazonS3ClientService.downloadFile(notesBucket, bookId);
            Note note = noteOptional.get();
            //note.setNoteImage(noteImage);
            //book.setNotes(note);
        }
        return book;
    }
}
