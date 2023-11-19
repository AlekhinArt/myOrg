package ru.egar.myOrg.worker.model;

import lombok.Getter;

@Getter
public enum TypeOfValue {
    CAR("Автомобиль"),
    ELECTRONICS("Электроника"),
    NOTEBOOK("Компьютерная техника"),
    DIGITAL_PRODUCT("Электронный продукт");

    private String typeV;

    TypeOfValue(String typeV) {
        this.typeV = typeV;
    }



    }
