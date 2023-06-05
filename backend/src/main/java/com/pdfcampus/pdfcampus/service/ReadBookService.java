package com.pdfcampus.pdfcampus.service;
import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Page;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import com.pdfcampus.pdfcampus.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReadBookService {
    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    private S3PresignedURLService s3PresignedURLService;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PageRepository pageRepository;

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

}
