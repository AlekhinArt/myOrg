package ru.egar.myOrg.document.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.mapper.PassportMapper;
import ru.egar.myOrg.document.model.PaperDocument;
import ru.egar.myOrg.document.repository.PassportRepository;
import ru.egar.myOrg.exception.DataConflictException;
import ru.egar.myOrg.exception.NotFoundException;


@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final PassportRepository pr;

    @Override
    public void save(PaperDocument paperDocument) {
        try {
            pr.save(paperDocument);
        } catch (Exception e) {
            throw new DataConflictException("Паспортные данные не сохранены");

        }
    }

    @Override
    public PassportDto findByWorkerIdAndActualTrue(Long workerId) {
        return PassportMapper.toPassportDto(pr.findByWorkerIdAndActualTrue(workerId));
    }

    //true - обновляем false - меняем
    @Override
    public void updPas(PassportDto passportDto, String whatDo) {
        PaperDocument oldPas = pr.findById(passportDto.getId()).orElseThrow(() -> new NotFoundException("Паспорт не найден"));
        if (Boolean.valueOf(whatDo)) {
            oldPas.setIssued(passportDto.getIssued());
            oldPas.setWhoIssued(passportDto.getWhoIssued());
            oldPas.setSeries(passportDto.getSeries());
            oldPas.setNumber(passportDto.getNumber());
            pr.save(oldPas);
        } else {
            oldPas.setActual(false);
            pr.save(oldPas);
            PaperDocument newPas = new PaperDocument();
            newPas.setWorker(oldPas.getWorker());
            newPas.setActual(true);
            newPas.setIssued(passportDto.getIssued());
            newPas.setWhoIssued(passportDto.getWhoIssued());
            newPas.setSeries(passportDto.getSeries());
            newPas.setNumber(passportDto.getNumber());
            newPas.setTypeDocument(passportDto.getCodeType());
            pr.save(newPas);
        }
    }


}
