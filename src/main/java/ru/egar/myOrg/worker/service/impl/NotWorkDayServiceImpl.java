package ru.egar.myOrg.worker.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.DataConflictException;
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
    private final NotWorksDaysRepository nwdRepo;

    @Override
    public List<NotWorksDays> getAll() {
        return nwdRepo.findAll();
    }

    @Override
    public Optional<NotWorksDays> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public NotWorksDays create(NotWorksDays dto) {
        return nwdRepo.save(dto);
    }

    @Override
    public NotWorksDays updateById(Long aLong, NotWorksDays notWorksDays) {
        notWorksDays.setNwdId(aLong);

        return nwdRepo.save(notWorksDays);
    }

    @Override
    public void deleteById(Long aLong) {
        nwdRepo.deleteById(aLong);

    }

    @Override
    public Collection<NotWorksDays> getAllByWhId(Long id) {
        return nwdRepo.findAllByWorkHistoryId(id);
    }


    @Override
    public NotWorksDays saveNotWorksDay(NotWorksDays nwd, Long whId) {
        for (NotWorksDays oldNwd : nwdRepo.findAllByWorkHistoryId(whId)) {
            if (!(nwd.getEnd().isBefore(oldNwd.getStart()) || nwd.getStart().isAfter(oldNwd.getEnd()))) {
                throw new DataConflictException("В эти дни работник уже " + oldNwd.getTypeDay());
            }
        }
        nwd.setWorkHistory(WorkHistory.builder().
                id(whId).
                build());
        return nwdRepo.save(nwd);
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
        if (type == null || type.isBlank()) {
            return nwdRepo.findAllByWorkHistoryIdDayAndStartAfterAndStartBefore(whId,
                    minDate, maxDate);
        }
        return nwdRepo.findAllByWorkHistoryIdAndTypeDayAndStartAfterAndStartBefore(whId,
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
