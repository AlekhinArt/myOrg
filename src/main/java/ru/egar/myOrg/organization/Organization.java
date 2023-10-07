package ru.egar.myOrg.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Organization {
    private int id;
    private String name;
    private int inn;
    private int ogrn;
    private String address;

}
