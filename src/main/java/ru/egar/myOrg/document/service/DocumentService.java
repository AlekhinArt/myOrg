package ru.egar.myOrg.document.service;

import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.model.BasePaperDocument;
import ru.egar.myOrg.document.model.Graduate;
import ru.egar.myOrg.document.model.Passport;
import ru.egar.myOrg.worker.model.enumerated.Gender;

public interface DocumentService {


    void save(Passport passport);

    void save(Graduate graduate);

   PassportDto findByWorkerIdAndActualTrue(Long workerId);

    //true - обновляем false - меняем
    void updPas(PassportDto passportDto, String whatDo);
}
