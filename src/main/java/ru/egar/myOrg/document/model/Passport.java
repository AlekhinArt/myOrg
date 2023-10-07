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
public class Passport extends PaperDocument {
    private int id;
    private static final int code= 1;
    private int number;
    private int series;
    private LocalDate issued;
    private String whoIssued;


}
