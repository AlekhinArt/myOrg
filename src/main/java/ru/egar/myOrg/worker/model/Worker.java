package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1, max = 40, message ="Должно быть не меньше одного и не более 40 символов")
    private String name;
    @Size(min = 1, max = 40, message ="Должно быть не меньше одного и не более 40 символов")
    private String surname;
    @Size(min = 1, max = 40, message ="Должно быть не меньше одного и не более 40 символов")
    private String patronymic;

    private LocalDate birthday;
    @Pattern(regexp = "[0-9]{11}", message ="Укажите телефонный номер в правильном формате")
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
    @OneToMany
    @JoinTable(name = "worker_valuable_object",
            joinColumns = {@JoinColumn(name = "worker_id")},
            inverseJoinColumns = {@JoinColumn(name = "obj_id")})
    private List <ValuableObject> valuableObjects;


}
