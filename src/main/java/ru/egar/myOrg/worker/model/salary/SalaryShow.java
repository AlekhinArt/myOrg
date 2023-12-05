package ru.egar.myOrg.worker.model.salary;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaryShow {
    private Double baseRate;

    private Double indexRate;

    private String sumMoney;
    private Integer sumHours;
    private Integer allWorkHours;
}
