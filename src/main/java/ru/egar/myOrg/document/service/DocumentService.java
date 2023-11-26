package ru.egar.myOrg.document.service;

import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.model.PaperDocument;

public interface DocumentService {


    void save(PaperDocument paperDocument);


    PassportDto findByWorkerIdAndActualTrue(Long workerId);

    //true - обновляем false - меняем
    void updPas(PassportDto passportDto, String whatDo);
}
