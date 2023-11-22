package ru.egar.myOrg.worker.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.DataConflictException;
import ru.egar.myOrg.exception.ValidException;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.repository.NotWorksDaysRepository;
import ru.egar.myOrg.worker.service.NotWorksDayService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class NotWorkDayServiceImpl implements NotWorksDayService {
    private final NotWorksDaysRepository nwdRep;

    @Override
    public List<NotWorksDays> getAll() {
        return null;
    }

    @Override
    public Optional<NotWorksDays> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public NotWorksDays create(NotWorksDays dto) {
        return null;
    }

    @Override
    public NotWorksDays updateById(Long aLong, NotWorksDays notWorksDays) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        nwdRep.deleteById(aLong);

    }

    @Override
    public Collection<NotWorksDays> getAllByWhId(Long id) {
        return nwdRep.findAllByWorkHistoryId(id);
    }

    @Override
    public Collection<NotWorksDays> getAllByWhIdAndType() {
        return null;
    }

    @Override
    public NotWorksDays saveNotWorksDay(NotWorksDays nwd, Long whId) {
        if (nwd.getEnd().isBefore(nwd.getStart()) || nwd.getStart().isAfter(nwd.getEnd())) {
            throw new ValidException("Не корректно указаны даты начала и окончания не рабочих дней");
        }
        for (NotWorksDays oldNwd : nwdRep.findAllByWorkHistoryId(whId)) {
            if (oldNwd.getStart().isBefore(nwd.getStart()) && oldNwd.getEnd().isAfter(nwd.getEnd())
                    || oldNwd.getStart().isBefore(nwd.getStart()) && oldNwd.getEnd().isBefore(nwd.getEnd())
                    || oldNwd.getStart().isAfter(nwd.getStart()) && oldNwd.getEnd().isAfter(nwd.getEnd())
                    || oldNwd.getStart().isAfter(nwd.getStart()) && oldNwd.getEnd().isBefore(nwd.getEnd())) {
                throw new DataConflictException(" Не корректно указаны даты");
            }
        }
        nwd.setWorkHistory(WorkHistory.builder().id(whId).build());
        return nwdRep.save(nwd);

    }

    @Override
    public Collection<NotWorksDays> notWorkDayByTypeAndDate(String type, Long whId, String start, String end) {
        LocalDate maxDate;
        LocalDate minDate;
        if (start == null || start.isEmpty()) {
            minDate = LocalDate.now().minusYears(50);
        } else minDate = LocalDate.parse(start, DateTimeFormatter.ISO_LOCAL_DATE);
        if (end == null || end.isEmpty()) {
            maxDate = LocalDate.now().plusYears(50);
        } else maxDate = LocalDate.parse(end, DateTimeFormatter.ISO_LOCAL_DATE);
        log.info("max {} , min {}", maxDate, minDate);
        return nwdRep.findAllByWorkHistoryIdAndTypeDayAndStartAfterAndStartBefore(whId,
                type, minDate, maxDate);
    }

    @Override
    public Long getSumNotWorkDays(Collection<NotWorksDays> nwds) {
        if (nwds == null) return 0L;
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
