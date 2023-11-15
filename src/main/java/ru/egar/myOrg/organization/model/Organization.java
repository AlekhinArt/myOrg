package ru.egar.myOrg.organization.model;

import jakarta.persistence.*;
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
    private String name;
    private String inn;
    private String ogrn;
    private String address;
    private String zip;
    @Column(name = "phone_number")
    private String phoneNumber;


}
