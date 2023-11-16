package ru.egar.myOrg.organization.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Long id;
    @Size(min = 1, max = 40, message ="Должно быть не меньше одного и не более 30 символов")
    private String name;
    @Size(max = 12, min = 12)
    @Pattern(regexp = "[0-9]{12}", message ="Укажите ИНН в правильном формате")
    private String inn;
    @Pattern(regexp = "[0-9]{13}", message ="Укажите ОГРН в правильном формате")
    private String ogrn;
    @Size(min = 1, max = 50, message ="Должно быть не меньше одного и не более 50 символов")
    private String address;
    @Pattern(regexp = "[0-9]{6}",  message ="Укажите почтовый кода в правильном формате")
    private String zip;
    @Pattern(regexp = "[0-9]{11}", message ="Укажите телефонный номер в правильном формате")
    @Column(name = "phone_number")
    private String phoneNumber;
}
