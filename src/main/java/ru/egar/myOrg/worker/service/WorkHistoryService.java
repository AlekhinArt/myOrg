package ru.egar.myOrg.worker.service;


import jakarta.xml.bind.ValidationException;
import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.model.notWorksDays.TypeOffDay;

import java.util.Collection;


public interface WorkHistoryService extends BaseService<WorkHistory, Long> {
    WorkHistory saveNotWorksDay(NotWorksDays nwd, Long id) throws ValidationException;

    void layOffWorker(WorkHistory wh, Long whId);

    Collection<NotWorksDays> notWorkDayByType(TypeOffDay type, Long whId);

    Long getAllNotWorkDays(Collection<NotWorksDays> nwds);

}
