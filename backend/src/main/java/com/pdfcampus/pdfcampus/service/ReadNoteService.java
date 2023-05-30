package com.pdfcampus.pdfcampus.service;
import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.NoteRepository;
import com.pdfcampus.pdfcampus.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.net.URL;


@Service
public class ReadNoteService {
    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    private S3PresignedURLService s3PresignedURLService;

    @Autowired
    private NoteRepository noteRepository; //노트 있는 경우
    // 추후 노트 있는 경우 검사하고, 합쳐서 전송하는 로직 추가

    public URL getBookCoverUrl(String noteId) {
        // 책의 표지 이미지가 저장된 S3의 URL을 생성
        String bucketName = "pdfampus";
        String objectKey = noteId + ".jpg";
        return s3PresignedURLService.generatePresignedUrl(bucketName, objectKey);
    }

    public URL getNotePdfUrl(String noteId) {
        Note note = noteRepository.findById(Integer.valueOf(noteId))
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id " + noteId));
        // noteTitle로 pdf url 생성(presigned)
        String bucketName = "8282note";
        String objectKey = note.getNoteTitle() + ".pdf";
        return s3PresignedURLService.generatePresignedUrl(bucketName, objectKey);
    }
}
