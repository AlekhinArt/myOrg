package ru.egar.myOrg.worker.service.impl;

import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.exception.ValidException;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.model.notWorksDays.TypeOffDay;
import ru.egar.myOrg.worker.repository.NotWorksDaysRepository;
import ru.egar.myOrg.worker.repository.WorkHistoryRepository;
import ru.egar.myOrg.worker.service.WorkHistoryService;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public class WorkerHistoryService implements WorkHistoryService {
    private final WorkHistoryRepository workHistoryRepository;
    private final NotWorksDaysRepository notWorksDaysRepository;

    public WorkerHistoryService(WorkHistoryRepository workHistoryRepository, NotWorksDaysRepository notWorksDaysRepository) {
        this.workHistoryRepository = workHistoryRepository;
        this.notWorksDaysRepository = notWorksDaysRepository;
    }

    @Override
    public List<WorkHistory> getAll() {
        return workHistoryRepository.findAll();
    }

    @Override
    public Optional<WorkHistory> getById(Long aLong) {
        return workHistoryRepository.findById(aLong);
    }

    @Override
    public WorkHistory create(WorkHistory dto) {
        return workHistoryRepository.save(dto);
    }

    @Override
    public WorkHistory updateById(Long aLong, WorkHistory workHistory) {

        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        workHistoryRepository.deleteById(aLong);

    }

    @Override
    public WorkHistory saveNotWorksDay(NotWorksDays nwd, Long whId) {
        if (nwd.getEnd().isBefore(nwd.getStart()) || nwd.getStart().isAfter(nwd.getEnd())) {
            throw new ValidException("Не корректно указаны даты начала и окончания не рабочих дней");
        }
        WorkHistory wh = workHistoryRepository.findById(whId).orElseThrow(() -> new NotFoundException("История не найдена"));
        wh.getNotWorksDays().add(nwd);
        nwd.setWorkHistory(wh);
        notWorksDaysRepository.save(nwd);
        return workHistoryRepository.save(wh);
    }

    @Override
    public void layOffWorker(WorkHistory wh, Long whId) {
        WorkHistory whOld = workHistoryRepository.findById(whId).orElseThrow(() -> new NotFoundException("История не найдена"));
        whOld.setEndWork(wh.getEndWork());
        whOld.setWorkNow(false);

        workHistoryRepository.save(whOld);
    }

    @Override
    public Collection<NotWorksDays> notWorkDayByTypeAndDate(TypeOffDay type, Long whId, LocalDate start, LocalDate end) {
        return notWorksDaysRepository.findAllByWorkHistory_IdAndTypeDay(whId, type, start, end);
    }

    @Override
    public Long getAllNotWorkDays(Collection<NotWorksDays> nwds) {
        long sumDays = 0L;
        for (NotWorksDays nwd : nwds) {
            long a = nwd.getEnd().toEpochDay() - nwd.getStart().toEpochDay();
            if (a == 0L) {
                sumDays++;
            } else sumDays = sumDays + a + 1L;

        }
        return sumDays;
    }

}
