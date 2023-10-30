package ru.egar.myOrg.organization.dto;


import lombok.Builder;
import lombok.Data;
import ru.egar.myOrg.worker.model.Worker;

import java.util.List;
@Builder
@Data
public class OrganizationDto {

    // TODO: 28.10.2023 добавить валидаторы здесь и в других дто
    private Long id;
    private String name;
    private String inn;
    private String ogrn;
    private String address;
    private String phoneNumber;
    private String zip;


}
