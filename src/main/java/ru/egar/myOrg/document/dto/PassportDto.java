package ru.egar.myOrg.document.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class PassportDto {
    private Long id;
    private String number;
    private String series;
    private LocalDate issued;
    private String whoIssued;
}
