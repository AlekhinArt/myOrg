package ru.egar.myOrg.worker;

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
    private int id;
    private boolean workNow;
    private EmployPosition employPosition;
    private LocalDate startWork;
    private LocalDate endWork;
    private List<LocalDate> daysOf;
    private List<LocalDate> sickDays;
    private List<LocalDate> vacation;


}
