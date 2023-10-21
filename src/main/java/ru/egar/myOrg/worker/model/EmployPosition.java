package ru.egar.myOrg.worker.model;

import lombok.*;

@Setter
@Getter
@Builder
public class EmployPosition {
    private Long id;
    private String position;
    private String jobDescription;


}
