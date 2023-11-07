package ru.egar.myOrg.worker.dto;

import lombok.*;
import ru.egar.myOrg.organization.model.Organization;

import java.time.LocalDate;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerCreateDto {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;
    private Organization organization;
    private Boolean workNow;
    private String employPosition;
    private LocalDate startWork;

}
