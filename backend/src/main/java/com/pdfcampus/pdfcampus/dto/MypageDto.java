package com.pdfcampus.pdfcampus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class MypageDto {
    private int uid;
    private String username;
    private boolean isSubscribed;
    private String productName;
    private LocalDate subscribeDate;
    private LocalDate joinedDate;

}
