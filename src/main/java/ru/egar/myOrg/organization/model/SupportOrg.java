package ru.egar.myOrg.organization.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "support_org")
//Класс регулировки
public class SupportOrg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sup_id")
    private Long id;
    //отправлять ли поздравления с днем рождения
    private Boolean sendEmailBirthday;


}
