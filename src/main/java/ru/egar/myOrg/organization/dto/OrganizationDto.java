package ru.egar.myOrg.organization.dto;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrganizationDto {

    @PositiveOrZero
    private Long id;
    @Size(min = 1, max = 40, message = "Должно быть не меньше одного и не более 30 символов")
    private String name;
    @Pattern(regexp = "[0-9]{12}", message = "Укажите ИНН в правильном формате")
    private String inn;
    @Pattern(regexp = "[0-9]{12}", message = "Укажите ИНН в правильном формате")
    private String ogrn;
    @Size(min = 1, max = 50, message = "Должно быть не меньше одного и не более 50 символов")
    private String address;
    @Pattern(regexp = "[0-9]{11}", message = "Укажите телефонный номер в правильном формате")
    private String phoneNumber;
    @Pattern(regexp = "[0-9]{6}", message = "Укажите почтовый кода в правильном формате")
    private String zip;


}
