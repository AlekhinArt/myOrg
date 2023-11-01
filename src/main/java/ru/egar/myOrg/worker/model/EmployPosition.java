package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;

// TODO: 31.10.2023 Сделать контроллер, добавить в выезжающий список выбор готовых позиций в newWorker
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name ="employ_pos")
public class EmployPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empl_id")
    private Long id;
    private String position;
    @Column(name = "job_description")
    private String jobDescription;


}
