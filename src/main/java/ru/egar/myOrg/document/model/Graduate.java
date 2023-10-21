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
public class Graduate extends BasePaperDocument {
//    private Long id;
//    private static final int code= 2;
    private String institution;
    private String grade;
    private String specialization;
    private LocalDate startDate;
    private LocalDate lastDate;
    public Graduate(){
        codeTypeDocument = "002";
        nameDocument = "Квалификационный документ";
    }


}
