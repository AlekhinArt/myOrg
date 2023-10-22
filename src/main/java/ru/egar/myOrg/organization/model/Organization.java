package ru.egar.myOrg.organization.model;

import jakarta.persistence.*;
import lombok.*;
import ru.egar.myOrg.worker.model.Worker;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Long id;
    private String name;
    private Integer inn;
    private Integer ogrn;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Worker> workers;

}
