package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;
import ru.egar.myOrg.document.model.BasePaperDocument;



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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "worker_id")
    private Long id;
    @Column(name ="work_now")
    private Boolean workNow;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;

//    private List<BasePaperDocument> documents;

//    @JoinTable(name = "WORKER_WORK_HISTORY",
//            joinColumns = {@JoinColumn(name = "worker_id")},
//            inverseJoinColumns = {@JoinColumn(name = "work_history_id")})
//@OneToMany(fetch = FetchType.LAZY, mappedBy = "work_history")
    @OneToMany(mappedBy = "worker")
//    @JoinColumn(name = "work_history_id", referencedColumnName = "worker_id")
    private List<WorkHistory> workHistory = new ArrayList<>();
//    private List <ValuableObject> valuableObjects;

}
