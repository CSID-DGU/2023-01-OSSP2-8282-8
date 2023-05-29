package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.DetailBookDto;
import com.pdfcampus.pdfcampus.dto.DetailNoteDto;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ReadNoteService {
    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    private NoteRepository noteRepository;

    private DetailService detailService;

    public DetailNoteDto getNoteById(String noteId) {
        Note note = noteRepository.findById(Integer.valueOf(noteId))
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id " + noteId));

        // 책의 표지 이미지가 저장된 S3의 URL을 생성
        String noteCoverUrl = "https://pdfampus.s3.ap-northeast-2.amazonaws.com/" + noteId + ".jpg";

        // Note 객체를 불러옴

        // DetailNoteDto 객체를 생성하고 반환
        return new DetailNoteDto(
                note.getNid(),
                note.getNoteTitle(),
                note.getAuthor(),
                note.getCreatedAt(),
                note.getModifiedAt(),
                "0",
                true,
                note.getBook().getPublisher(),
                note.getUser().getUid(),
                note.getBook().getPublisher(),
                note.getBook().getPublicationYear(),
                noteCoverUrl
        );
    }
}
