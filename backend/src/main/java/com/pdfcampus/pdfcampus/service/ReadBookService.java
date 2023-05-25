package com.pdfcampus.pdfcampus.service;
import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.NoteRepository;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;


@Service
public class ReadBookService {
    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private BookRepository bookRepository;

    public DetailBookDto getBookById(String bookId) {
        System.out.print("Service:" + bookId + "\n");
        Book book = bookRepository.findById(Integer.valueOf(bookId))
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + bookId));
        System.out.print("BookTitle"+book.getBookTitle());
        // 책의 표지 이미지가 저장된 S3의 URL을 생성
        String bookCoverUrl = "https://pdfampus.s3.ap-northeast-2.amazonaws.com/" + bookId + ".jpg";

        // Note 객체를 불러옴
        //boolean hasNote = noteRepository.findByBook(Integer.valueOf(bookId)).isPresent();

        //System.out.print("hasNote:" + hasNote + "\n");
        System.out.print(book.getBid());
        // DetailBookDto 객체를 생성하고 반환
        return new DetailBookDto(
                book.getBid(),
                book.getBookTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublicationYear(),
                bookCoverUrl,
                true
        );
    }
}
