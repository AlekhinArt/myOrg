package ru.egar.myOrg.worker.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.egar.myOrg.worker.model.EmployPosition;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class WorkerHistoryDto {
    private Long id;
    private boolean workNow;
    private EmployPosition employPosition;
    private LocalDate startWork;
    private LocalDate endWork;

    private List<LocalDate> daysOf;

    private List<LocalDate> sickDays;

    private List<LocalDate> vacation;

}
