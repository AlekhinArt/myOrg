package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;
import ru.egar.myOrg.organization.model.Organization;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Long id;
    @Column(name = "work_now")
    private Boolean workNow;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(name = "worker_work_history",
            joinColumns = {@JoinColumn(name = "worker_id")},
            inverseJoinColumns = {@JoinColumn(name = "work_history_id")})
    private List<WorkHistory> workHistory = new ArrayList<>();
//    private List <ValuableObject> valuableObjects;

}
