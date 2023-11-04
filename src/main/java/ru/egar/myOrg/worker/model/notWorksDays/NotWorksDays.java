package ru.egar.myOrg.worker.model.notWorksDays;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@MappedSuperclass
public abstract class NotWorksDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "start_date")
    private LocalDate start;
    @Column(name = "end_date")
    private LocalDate end;
    private String note;




}
