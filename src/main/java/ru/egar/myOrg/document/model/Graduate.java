package ru.egar.myOrg.document.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
//Диплом
public class Graduate extends BasePaperDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graduate_id")
    private Long id;
    //Институт
    private String institution;
    //Квалификация
    private String grade;
    //Специальность
    private String specialization;
    private LocalDate startDate;
    private LocalDate lastDate;


}
