package ru.egar.myOrg.worker.dto;

import lombok.*;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.worker.model.WorkHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerDto {
    private Long id;
    private Boolean workNow;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;
    private Organization organization;
    private List<WorkHistory> workHistory = new ArrayList<>();

}
