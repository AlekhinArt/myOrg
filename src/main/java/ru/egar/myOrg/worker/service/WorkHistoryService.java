package ru.egar.myOrg.worker.service;


import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.WorkTableInfo;

import java.time.LocalDate;
import java.util.Collection;


public interface WorkHistoryService extends BaseService<WorkHistory, Long> {


    Collection<WorkHistory> getByWorkerId(Long workerId);

    void layOffWorker(WorkHistory wh, Long whId);


    void createNewWorkHistory(Long workerId, LocalDate startWork, Long emplPosId);


    void changeWorkerStatus(Long workerId);


    WorkTableInfo[][] getNotWorksDayInCalendar(Long whId, String start, String end);
}


