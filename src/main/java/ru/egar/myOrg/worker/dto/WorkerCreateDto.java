package ru.egar.myOrg.worker.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.egar.myOrg.organization.model.Organization;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerCreateDto {
    @Size(min = 1, max = 40, message = "Должно быть не меньше одного и не более 30 символов")
    private String name;
    @Size(min = 1, max = 40, message = "Должно быть не меньше одного и не более 30 символов")
    private String surname;
    @Size(min = 1, max = 40, message = "Должно быть не меньше одного и не более 30 символов")
    private String patronymic;
    private LocalDate birthday;
    @Pattern(regexp = "[0-9]{11}", message = "Укажите телефонный номер в правильном формате")
    private String phoneNumber;
    private Organization organization;
    private Boolean workNow;
    @Size(min = 1, max = 40, message = "Должно быть не меньше одного и не более 30 символов")
    private String employPosition;
    private LocalDate startWork;
    @PositiveOrZero
    private Long orgId;

}
