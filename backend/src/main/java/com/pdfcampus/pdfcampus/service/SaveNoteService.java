package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import com.pdfcampus.pdfcampus.repository.NoteRepository;
import com.pdfcampus.pdfcampus.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SaveNoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public SaveNoteService(NoteRepository noteRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public boolean saveNote(String userId, String bookId) {
        try {
            User user = (User) userRepository.findByUid(Integer.valueOf(userId));
            List<Book> books = bookRepository.findByBid(Integer.valueOf(bookId)); //어쩔 수 없이 List 사용
            if (!books.isEmpty()) {
                Book book = books.get(0);

                Note newNote = new Note();
                newNote.setUser(user);
                newNote.setBook(book);
                newNote.setNoteTitle(book.getBookTitle() + "NOTE");
                newNote.setAuthor(user.getUsername());
                newNote.setCreatedAt(LocalDate.now());
                newNote.setModifiedAt(LocalDate.now());

                noteRepository.save(newNote);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
