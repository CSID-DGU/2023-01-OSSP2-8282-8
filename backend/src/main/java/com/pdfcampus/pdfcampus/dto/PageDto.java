package com.pdfcampus.pdfcampus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
@AllArgsConstructor
@ToString
@Setter
@Getter
public class PageDto {
    private Integer pid;
    private Integer bid;
    private Integer pageNumber;
}
