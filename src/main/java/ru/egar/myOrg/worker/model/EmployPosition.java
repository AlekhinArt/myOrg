package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "employ_pos", uniqueConstraints = {
        @UniqueConstraint(columnNames = "cod_type"),
        @UniqueConstraint(columnNames = "id")
})
//должность
public class EmployPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    //Название
    private String position;
    //описание должности
    @Column(name = "job_description")
    private String jobDescription;
    @Column(name = "cod_type")
    private String codType;


}
