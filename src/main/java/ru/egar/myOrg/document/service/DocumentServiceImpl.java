package ru.egar.myOrg.document.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.mapper.PassportMapper;
import ru.egar.myOrg.document.model.Graduate;
import ru.egar.myOrg.document.model.Passport;
import ru.egar.myOrg.document.repository.GraduateRepository;
import ru.egar.myOrg.document.repository.PassportRepository;
import ru.egar.myOrg.exception.DataConflictException;


@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final PassportRepository pr;
    private final GraduateRepository gr;

    @Override
    public void save(Passport passport) {
        try {
            pr.save(passport);
        } catch (Exception e) {
            throw new DataConflictException("Паспортные данные не сохранены");

        }
    }

    @Override
    public void save(Graduate graduate) {
        try {
            gr.save(graduate);
        } catch (Exception e) {
            throw new DataConflictException("Данные Диплома не сохранены не сохранены");

        }
    }

    @Override
    public PassportDto findByWorkerIdAndActualTrue(Long workerId) {
        return PassportMapper.toPassportDto(pr.findByWorkerIdAndActualTrue(workerId));
    }

    public void updPas(PassportDto passportDto, String whatDo){



    }


}
