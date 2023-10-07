package ru.egar.myOrg.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.egar.myOrg.worker.Worker;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Organization {
    private int id;
    private String name;
    private int inn;
    private int ogrn;
    private String address;
    private String phoneNumber;
    private List<Worker> workers;

}
