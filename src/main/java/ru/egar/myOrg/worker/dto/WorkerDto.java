package ru.egar.myOrg.worker.dto;

import lombok.*;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.worker.model.ValuableObject;
import ru.egar.myOrg.worker.model.enumerated.FamilyStatus;
import ru.egar.myOrg.worker.model.enumerated.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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

}
