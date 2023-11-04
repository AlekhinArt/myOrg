package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;
import ru.egar.myOrg.worker.model.notWorksDays.DaysOf;
import ru.egar.myOrg.worker.model.notWorksDays.SickDays;
import ru.egar.myOrg.worker.model.notWorksDays.Vacation;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "workerH")
@Entity
@Table(name = "work_history")
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_history_id")
    private Long id;
    @Column(name = "work_now")
    private boolean workNow;
    @ManyToOne
    @JoinColumn(name = "empl_id")
    private EmployPosition employPosition;
    @Column(name = "start_work")
    private LocalDate startWork;
    @Column(name = "end_work")
    private LocalDate endWork;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker workerH;

    //    прогулы
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "days_of",
            joinColumns = {@JoinColumn(name = "wh_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<DaysOf> daysOf = new LinkedList<>();
    //    больничные
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sick_days",
            joinColumns = {@JoinColumn(name = "wh_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<SickDays> sickDays = new LinkedList<>();
    //    отпуск
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vacation",
            joinColumns = {@JoinColumn(name = "wh_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<Vacation> vacation = new LinkedList<>();


}
