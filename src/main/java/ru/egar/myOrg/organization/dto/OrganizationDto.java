package ru.egar.myOrg.organization.dto;


import lombok.Builder;
import lombok.Data;
import ru.egar.myOrg.worker.model.Worker;

import java.util.List;
@Builder
@Data
public class OrganizationDto {
    private Long id;
    private String name;
    private Integer inn;
    private Integer ogrn;
    private String address;
    private String phoneNumber;


}
