package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private Worker worker;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(name = "work_history_notWD",
            joinColumns = {@JoinColumn(name = "work_history_id")},
            inverseJoinColumns = {@JoinColumn(name = "nwd_id")})
    private List<NotWorksDays> notWorksDays = new LinkedList<>();


}
