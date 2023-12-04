package ru.egar.myOrg.document.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.mapper.PassportMapper;
import ru.egar.myOrg.document.model.PaperDocument;
import ru.egar.myOrg.document.repository.DocumentRepository;
import ru.egar.myOrg.document.service.DocumentService;
import ru.egar.myOrg.exception.DataConflictException;
import ru.egar.myOrg.exception.NotFoundException;


@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final PassportMapper pasMap;
    private final DocumentRepository pr;

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
        var documentByWorker = pr.findByWorkerIdAndActualTrue(workerId)
                .orElseThrow(() -> new NotFoundException("Документ не найдет"));
        return pasMap.toPassportDto(documentByWorker);
    }


    @Override
    public void updPas(PassportDto passportDto) {
        PaperDocument oldPas = pr.findById(passportDto.getId())
                .orElseThrow(() -> new NotFoundException("Паспорт не найден"));
        oldPas.setIssued(passportDto.getIssued());
        oldPas.setWhoIssued(passportDto.getWhoIssued());
        oldPas.setSeries(passportDto.getSeries());
        oldPas.setNumber(passportDto.getNumber());
        pr.save(oldPas);

    }

    @Override
    public void createNew(PassportDto passportDto) {
        PaperDocument oldPas = pr.findById(passportDto.getId())
                .orElseThrow(() -> new NotFoundException("Паспорт не найден"));
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
