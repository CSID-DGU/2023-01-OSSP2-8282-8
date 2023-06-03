package com.pdfcampus.pdfcampus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class ExtractedTextInfo {
    private String text;
    private int rowNumber;
    private float yPosition;

    public ExtractedTextInfo() {

    }
}

