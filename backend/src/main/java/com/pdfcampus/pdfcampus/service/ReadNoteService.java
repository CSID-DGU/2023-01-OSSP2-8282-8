package com.pdfcampus.pdfcampus.service;
import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.entity.*;
import com.pdfcampus.pdfcampus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReadNoteService {
    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    private S3PresignedURLService s3PresignedURLService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DetailNoteRepository detailNoteRepository;

    @Autowired
    private ReadRepository readRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private NotePageRepository notePageRepository;

    public String getBookId(String noteId) {
        Integer nidInt = Integer.parseInt(noteId);
        Note note = detailNoteRepository.findByNid(nidInt).orElseThrow(() -> new NullPointerException("Note not found"));
        int bookId = note.getBook().getBid();

        return Integer.toString(bookId);
    }
    public URL getBookCoverUrl(String noteId) {
        // 책의 표지 이미지가 저장된 S3의 URL을 생성
        String bucketName = "pdfampus";
        String objectKey = noteId + ".jpg";
        return s3PresignedURLService.generatePresignedUrl(bucketName, objectKey);
    }

    public List<String> getNotePdfUrls(String noteId) {
        Note note = noteRepository.findById(Integer.valueOf(noteId))
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id " + noteId));
        // noteTitle로 pdf url 생성(presigned)
        List<NotePage> pages = notePageRepository.findByNid(note.getNid());
        List<String> pageUrls = new ArrayList<>();
        for (NotePage page : pages) {
            pageUrls.add(page.getPageUrl());
        }

        return pageUrls;
    }

    public List<String> getBookPdfUrls(String bookId) {
        Book book = bookRepository.findById(Integer.valueOf(bookId))
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + bookId));

        List<Page> pages = pageRepository.findByBid(book.getBid());
        List<String> pageUrls = new ArrayList<>();
        for (Page page : pages) {
            pageUrls.add(page.getPageUrl());
        }

        return pageUrls;
    }

    public boolean isUserSubscribed(String id) {
        Integer uidInt = Integer.parseInt(id);
        User user = readRepository.findByUid(uidInt);
        if (user != null) {
            return user.isSubscribed();
        } else {
            return false; // 예외 처리
        }
    }
}
