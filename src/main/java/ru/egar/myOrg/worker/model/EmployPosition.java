package ru.egar.myOrg.worker.model;

import lombok.*;

@Setter
@Getter
@Builder
public class EmployPosition {
    private int id;
    private String position;
    private String jobDescription;


}
