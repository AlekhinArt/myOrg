package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "work_history")
//История работы в организации
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_history_id")
    private Long id;
    @Column(name = "work_now")
    //работает ли сейчас
    private boolean workNow;
    @ManyToOne
    @JoinColumn(name = "empl_id")

    private EmployPosition employPosition;
    @Column(name = "start_work")
    //Дата начала работы на должности
    private LocalDate startWork;
    @Column(name = "end_work")
    //Конец работы на должности
    private LocalDate endWork;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
