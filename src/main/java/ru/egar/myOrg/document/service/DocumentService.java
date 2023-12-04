package ru.egar.myOrg.document.service;

import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.model.PaperDocument;

public interface DocumentService {


    void save(PaperDocument paperDocument);


    PassportDto findByWorkerIdAndActualTrue(Long workerId);


    void updPas(PassportDto passportDto);

    void createNew(PassportDto passportDto);
}
