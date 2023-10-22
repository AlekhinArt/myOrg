package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
//@Entity
public class ValuableObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "obg_id")
    private Long id;
    @Column(name= "is_use")
    private Boolean isUse;
    private String name;
    private float price;
    @Column(name= "org_id")
    private LocalDate dateOfPurchase;
    private TypeOfValue typeOfValue;


}
