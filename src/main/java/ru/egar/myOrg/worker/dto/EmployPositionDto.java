package ru.egar.myOrg.worker.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployPositionDto {
    private Long id;
    private String position;
    private String jobDescription;
    private String codType;
}
