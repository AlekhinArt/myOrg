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

@Table(name = "paper_document", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"number", "series"})
})
//Документ
public class PaperDocument extends BasePaperDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String number;

    private String series;
    //Когда выдан

    private LocalDate issued;
    // Кем выдан

    private String whoIssued;

    //дополнительные поля
    private String additionalOne;
    private String additionalTwo;


}
