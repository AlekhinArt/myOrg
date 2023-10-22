package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import lombok.*;
import ru.egar.myOrg.document.model.BasePaperDocument;



import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Worker {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="work_now")
    private Boolean workNow;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;

//    private List<BasePaperDocument> documents;
    @OneToMany(fetch = FetchType.LAZY)
private List<WorkHistory> workHistory;
//    private List <ValuableObject> valuableObjects;

}
