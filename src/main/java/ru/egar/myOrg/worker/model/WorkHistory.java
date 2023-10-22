package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;
import ru.egar.myOrg.worker.model.notWorksDays.DaysOf;
import ru.egar.myOrg.worker.model.notWorksDays.SickDays;
import ru.egar.myOrg.worker.model.notWorksDays.Vacation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_history_id")
    private Long id;
    @Column(name = "work_now")
    private boolean workNow;
    @OneToOne
    @Column(name = "employ_position")
    private EmployPosition employPosition;
    @Column(name = "start_work")
    private LocalDate startWork;
    @Column(name = "end_work")
    private LocalDate endWork;
    //    прогулы
    @OneToMany(fetch = FetchType.LAZY)
    private List<DaysOf> daysOf = new LinkedList<>();
    //    больничные
    @OneToMany(fetch = FetchType.LAZY)
    private List<SickDays> sickDays = new LinkedList<>();
    //    отпуск
    @OneToMany(fetch = FetchType.LAZY)
    private List<Vacation> vacation = new LinkedList<>();


}
