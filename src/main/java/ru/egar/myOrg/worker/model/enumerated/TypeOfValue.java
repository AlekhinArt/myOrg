package ru.egar.myOrg.worker.model.enumerated;

import lombok.Getter;

@Getter
public enum TypeOfValue {
    CAR("Автомобиль"),
    ELECTRONICS("Электроника"),
    NOTEBOOK("Компьютерная техника"),
    DIGITAL_PRODUCT("Электронный продукт");

    private final String typeV;

    TypeOfValue(String typeV) {
        this.typeV = typeV;
    }


}
