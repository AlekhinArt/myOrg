package ru.egar.myOrg.worker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.worker.model.ValuableObject;
import ru.egar.myOrg.worker.model.enumerated.FamilyStatus;
import ru.egar.myOrg.worker.model.enumerated.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkerDto {

    private Long id;
    private Boolean workNow;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;
    private Long orgId;
    private Organization organization;
    private FamilyStatus familyStatus;
    private Boolean minorChildren;
    private Gender gender;
    private String email;
    private List<ValuableObject> valuableObjects = new ArrayList<>();
    private String employPosition;

}
