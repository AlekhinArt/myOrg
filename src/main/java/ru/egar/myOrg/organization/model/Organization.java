package ru.egar.myOrg.organization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.egar.myOrg.worker.model.Worker;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Organization {
    private Long id;
    private String name;
    private Integer inn;
    private Integer ogrn;
    private String address;
    private String phoneNumber;
    private List<Worker> workers;

}
