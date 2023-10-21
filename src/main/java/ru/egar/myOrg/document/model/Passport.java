package ru.egar.myOrg.document.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Passport extends BasePaperDocument {
//    private Long id;
//    private static final int code= 1;
    private Integer number;
    private Integer series;
    private LocalDate issued;
    private String whoIssued;
    public Passport(){
        codeTypeDocument = "001";
        nameDocument = "Пасспорт";
    }

}
