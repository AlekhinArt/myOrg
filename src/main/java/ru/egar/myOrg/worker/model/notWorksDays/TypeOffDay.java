package ru.egar.myOrg.worker.model.notWorksDays;

import lombok.Getter;
import lombok.ToString;

@Getter
public enum TypeOffDay {
    SICK_DAY("Больничный"),
    VACATION("Больничный"),
    DAY_OF("Больничный");

    private String type;
    TypeOffDay (String type){
        this.type = type;
    }

}
