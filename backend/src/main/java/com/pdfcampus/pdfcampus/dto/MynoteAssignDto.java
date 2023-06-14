package com.pdfcampus.pdfcampus.dto;

import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.entity.Sale;
import com.pdfcampus.pdfcampus.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class MynoteAssignDto {
    private Integer sid;
    private Integer userId;
    private Integer noteId;
    private Integer price;

    public Sale toEntity(Note note, Integer price) {
        return new Sale(sid, note, null, price);
    }
}
