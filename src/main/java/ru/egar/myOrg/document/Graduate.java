package ru.egar.myOrg.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class Graduate extends PaperDocument {
    private int id;
    private static final int code= 2;
    private String institution;
    private String grade;
    private String specialization;
    private LocalDate startDate;
    private LocalDate lastDate;


}
