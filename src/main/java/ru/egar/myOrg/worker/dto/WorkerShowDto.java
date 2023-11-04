package ru.egar.myOrg.worker.dto;

import lombok.*;
import ru.egar.myOrg.worker.model.EmployPosition;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerShowDto {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;
    private String employPosition;






}
