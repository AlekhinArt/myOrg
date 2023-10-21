package ru.egar.myOrg.worker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class WorkHistory {
    private Long id;
    private boolean workNow;
    private EmployPosition employPosition;
    private LocalDate startWork;
    private LocalDate endWork;
//    прогулы
    private List<LocalDate> daysOf;
//    больничные
    private List<LocalDate> sickDays;
//    отпуск
    private List<LocalDate> vacation;


}
