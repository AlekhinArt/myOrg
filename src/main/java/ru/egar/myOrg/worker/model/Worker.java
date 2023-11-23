package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.worker.model.enumerated.FamilyStatus;
import ru.egar.myOrg.worker.model.enumerated.Gender;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "worker")
//Работник
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "work_now")
    //Работает сейчас
    private Boolean workNow;
    @Size(min = 1, max = 40, message = "Должно быть не меньше одного и не более 40 символов")
    private String name;
    @Size(min = 1, max = 40, message = "Должно быть не меньше одного и не более 40 символов")
    private String surname;
    @Size(min = 1, max = 40, message = "Должно быть не меньше одного и не более 40 символов")
    private String patronymic;

    private LocalDate birthday;
    @Pattern(regexp = "[0-9]{11}", message = "Укажите телефонный номер в правильном формате")
    private String phoneNumber;
    //пометка удаления
    private Boolean delete;
    //Семейный статус
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    //несовершеннолетние дети
    private Boolean minorChildren;
    //Пол
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;


}
