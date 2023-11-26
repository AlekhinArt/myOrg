package ru.egar.myOrg.document.mapper;

import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.model.PaperDocument;

public class PassportMapper {

    public static PassportDto toPassportDto(PaperDocument paperDocument) {

        return PassportDto.builder()
                .id(paperDocument.getId())
                .series(paperDocument.getSeries())
                .number(paperDocument.getSeries())
                .issued(paperDocument.getIssued())
                .whoIssued(paperDocument.getWhoIssued())
                .codeType(paperDocument.getTypeDocument())

                .build();
    }
}
