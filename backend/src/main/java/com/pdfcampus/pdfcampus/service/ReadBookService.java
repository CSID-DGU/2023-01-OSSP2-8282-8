package com.pdfcampus.pdfcampus.service;
import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.NoteRepository;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@Service
public class ReadBookService {
    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    private S3PresignedURLService s3PresignedURLService;

    @Autowired
    private NoteRepository noteRepository; //노트 있는 경우
    // 추후 노트 있는 경우 검사하고, 합쳐서 전송하는 로직 추가

    @Autowired
    private BookRepository bookRepository;

    public URL getBookCoverUrl(String bookId) throws MalformedURLException {
        Book book = bookRepository.findById(Integer.valueOf(bookId))
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + bookId));
        String storedBookCoverUrl = book.getBookCover();

        // 이미 DB에 존재하는지 확인
        if (storedBookCoverUrl != null && isUrlValid(storedBookCoverUrl)) {
            return new URL(storedBookCoverUrl);
        }

        // 존재하지 않는다면 생성
        String bucketName = "pdfampus";
        String objectKey = bookId + ".jpg";
        URL newUrl = s3PresignedURLService.generatePresignedUrl(bucketName, objectKey);
        
        book.setBookCover(newUrl.toString());
        bookRepository.save(book);

        return newUrl;
    }

    private boolean isUrlValid(String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            return false;
        }
    }

    public URL getBookPdfUrl(String bookId) {
        Book book = bookRepository.findById(Integer.valueOf(bookId))
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + bookId));
        // bookTitle로 pdf url 생성(presigned)
        String bucketName = "8282book";
        String objectKey = book.getBookTitle() + ".pdf";
        return s3PresignedURLService.generatePresignedUrl(bucketName, objectKey);
    }
}
