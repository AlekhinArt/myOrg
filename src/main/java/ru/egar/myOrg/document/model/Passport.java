package ru.egar.myOrg.document.model;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor

@Builder
public class Passport extends BasePaperDocument {
//    private Long id;
//    private static final int code= 1;
    private String number;
    private String series;
    private LocalDate issued;
    private String whoIssued;
//    public Passport(){
//        codeTypeDocument = "001";
//        nameDocument = "Паспорт";
//    }

}
