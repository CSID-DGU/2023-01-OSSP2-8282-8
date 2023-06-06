package com.pdfcampus.pdfcampus.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.entity.NotePage;
import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import com.pdfcampus.pdfcampus.repository.NotePageRepository;
import com.pdfcampus.pdfcampus.repository.NoteRepository;
import com.pdfcampus.pdfcampus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
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
    private final NotePageRepository notePageRepository;
    @Autowired
    private AmazonS3 s3client;

    public SaveNoteService(NoteRepository noteRepository, UserRepository userRepository, BookRepository bookRepository, NotePageRepository notePageRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.notePageRepository = notePageRepository;
    }

    @Transactional
    public boolean saveNote(String userId, String bookId, Map<String, String> notes) {
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

                // Loop over the notes and save them in the database and to the S3 bucket
                for (Map.Entry<String, String> entry : notes.entrySet()) {
                    String noteImageId = entry.getKey();
                    String noteImageUrl = entry.getValue();

                    // 1. Download the image from the URL
                    BufferedImage image = ImageIO.read(new URL(noteImageUrl));

                    // 2. Save the image to a temporary file
                    File imageFile = new File(noteImageId + ".jpg");
                    ImageIO.write(image, "jpg", imageFile);

                    // 3. Upload the file to S3
                    s3client.putObject(new PutObjectRequest("8282note", "8282note/" + noteImageId, imageFile));

                    // 4. Delete the temporary file
                    imageFile.delete();

                    // 5. Save the note in the database
                    NotePage notePage = new NotePage();
                    notePage.setNid(newNote.getNid());  // Assuming you have a getter for the ID in the Note entity
                    notePage.setNotepageNumber(Integer.valueOf(noteImageId));
                    notePage.setNotepageUrl("s3://" + "8282note" + "/8282note/" + noteImageId);
                    notePageRepository.save(notePage);

                }

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
