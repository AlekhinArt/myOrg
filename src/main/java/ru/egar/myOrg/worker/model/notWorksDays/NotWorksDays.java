package ru.egar.myOrg.worker.model.notWorksDays;

import jakarta.persistence.*;
import lombok.*;
import ru.egar.myOrg.worker.model.EmployPosition;
import ru.egar.myOrg.worker.model.WorkHistory;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "not_work_days")

public class NotWorksDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "start_date")
    private LocalDate start;
    @Column(name = "end_date")
    private LocalDate end;
    private String note;
    @Enumerated(EnumType.STRING)
    @Column (name = "type_day")
    private TypeOffDay typeDay;
    @ManyToOne
    @JoinColumn(name = "work_history_id")
    private WorkHistory workHistory;


}
