package com.pdfcampus.pdfcampus.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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

                for (Map.Entry<String, String> entry : notes.entrySet()) {
                    String noteImageId = entry.getKey();
                    String base64Image = entry.getValue();

                    // 프리픽스 제거 data:image/png;base64,
                    base64Image = base64Image.substring(base64Image.indexOf(",") + 1);

                    byte[] imageBytes = Base64.getDecoder().decode(base64Image);

                    // Convert to PNG
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ImageIO.write(img, "png", outputStream);
                    imageBytes = outputStream.toByteArray();

                    InputStream inputStream = new ByteArrayInputStream(imageBytes);

                    // 업로드
                    s3client.putObject(new PutObjectRequest("8282note", newNote.getNoteTitle()+"/" + noteImageId + ".png", inputStream, new ObjectMetadata()));

                    // DB에 추가
                    NotePage notePage = new NotePage();
                    notePage.setNid(newNote.getNid());
                    notePage.setNotepageNumber(Integer.valueOf(noteImageId));
                    notePage.setNotepageUrl("s3://" + "8282note" + newNote.getNoteTitle() + noteImageId + ".png");
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
