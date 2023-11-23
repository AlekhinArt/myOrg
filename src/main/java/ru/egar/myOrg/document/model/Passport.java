package ru.egar.myOrg.document.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotBlank
    private String number;
    @NotNull
    @NotBlank
    private String series;
    //Когда выдан
    @NotNull
    private LocalDate issued;
    // Кем выдан
    @NotNull
    @NotBlank
    private String whoIssued;


}
