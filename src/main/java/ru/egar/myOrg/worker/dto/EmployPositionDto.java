package ru.egar.myOrg.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class EmployPositionDto {
    private String position;
    private String jobDescription;
}
