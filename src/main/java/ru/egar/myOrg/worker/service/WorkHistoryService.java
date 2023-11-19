package ru.egar.myOrg.worker.service;


import jakarta.xml.bind.ValidationException;
import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;

import java.time.LocalDate;
import java.util.Collection;


public interface WorkHistoryService extends BaseService<WorkHistory, Long> {
    WorkHistory saveNotWorksDay(NotWorksDays nwd, Long id) throws ValidationException;

    void layOffWorker(WorkHistory wh, Long whId);

    Collection<NotWorksDays> notWorkDayByTypeAndDate(String type, Long whId, String start, String end);

    Long getAllNotWorkDays(Collection<NotWorksDays> nwds);

    void createNewWorkHistory(Long workerId, LocalDate startWork, Long emplPosId);

}
