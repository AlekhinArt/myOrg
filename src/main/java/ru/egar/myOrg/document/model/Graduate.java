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
public class Graduate extends BasePaperDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graduate_id")
    private Long id;
    private String institution;
    private String grade;
    private String specialization;
    private LocalDate startDate;
    private LocalDate lastDate;


}
