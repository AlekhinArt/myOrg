package ru.egar.myOrg.document.mapper;

import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.model.Passport;

public class PassportMapper {

    public static PassportDto toPassportDto(Passport passport) {

        return PassportDto.builder()
                .id(passport.getId())
                .series(passport.getSeries())
                .number(passport.getSeries())
                .issued(passport.getIssued())
                .whoIssued(passport.getWhoIssued())
                .codeType(passport.getTypeDocument())
                .build();
    }
}
