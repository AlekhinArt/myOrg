package ru.egar.myOrg.worker.model.enumerated;

import lombok.Getter;

@Getter
public enum TypeOffDay {
    SICK_DAY("Больничный"),
    VACATION("Отпуск"),
    DAY_OF("Отгул");

    private String type;

    TypeOffDay(String type) {
        this.type = type;
    }

}
