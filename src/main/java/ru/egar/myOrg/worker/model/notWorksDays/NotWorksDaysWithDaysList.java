package ru.egar.myOrg.worker.model.notWorksDays;

import lombok.*;
import ru.egar.myOrg.worker.model.enumerated.TypeOffDay;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Не рабочие дни со списком
public class NotWorksDaysWithDaysList {

    private Long nwdId;
    private LocalDate start;
    private LocalDate end;
    private String note;
    private TypeOffDay typeDay;
    List<LocalDate> notWorksDays;
}
