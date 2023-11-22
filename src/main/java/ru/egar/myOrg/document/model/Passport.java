package ru.egar.myOrg.document.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
//Паспорт
public class Passport extends BasePaperDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    private Long id;
    private String number;
    private String series;
    //Когда выдан
    private LocalDate issued;
    // Кем выдан
    private String whoIssued;

}
