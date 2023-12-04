package ru.egar.myOrg.document.mapper;

import org.springframework.stereotype.Component;
import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.model.PaperDocument;

@Component
public class PassportMapper {

    public PassportDto toPassportDto(PaperDocument paperDocument) {

        return PassportDto.builder()
                .id(paperDocument.getId())
                .series(paperDocument.getSeries())
                .number(paperDocument.getNumber())
                .issued(paperDocument.getIssued())
                .whoIssued(paperDocument.getWhoIssued())
                .codeType(paperDocument.getTypeDocument())

                .build();
    }
}
