package ru.egar.myOrg.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.egar.myOrg.worker.model.TypeOfValue;

import java.time.LocalDate;
@Builder
@Setter
@Getter
@AllArgsConstructor
public class ValuableObjectDto {
    private Long id;
    private Boolean isUse;
    private String name;
    private float price;
    private LocalDate dateOfPurchase;
    private TypeOfValue typeOfValue;
}
