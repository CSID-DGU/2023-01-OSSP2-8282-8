package com.pdfcampus.pdfcampus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class RowNumDto {
    private Integer rid;
    private Integer pid;
    private Integer rowNumber;
    private Float rowY;

}
