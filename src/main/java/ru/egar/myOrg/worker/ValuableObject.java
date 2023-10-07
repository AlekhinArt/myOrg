package ru.egar.myOrg.worker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ValuableObject {
    private int id;
    private Boolean isUse;
    private String name;
    private float price;
    private LocalDate dateOfPurchase;


}
