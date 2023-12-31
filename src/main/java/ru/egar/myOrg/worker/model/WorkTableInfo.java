package ru.egar.myOrg.worker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkTableInfo {
    //количество часов
    private Integer hours;
    // где был
    private String whereBe;
    private int dateMonth;
    private boolean show;
    private boolean workDay;

}
