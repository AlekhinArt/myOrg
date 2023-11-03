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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_worker",
            joinColumns = {@JoinColumn(name = "org_id")},
            inverseJoinColumns = {@JoinColumn(name = "worker_id")})
    private List<Worker> workers;

}
