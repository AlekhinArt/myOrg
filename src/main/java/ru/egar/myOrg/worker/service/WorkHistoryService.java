package ru.egar.myOrg.worker.service;


import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.worker.dto.WorkerShowDto;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.Worker;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


public interface WorkHistoryService extends BaseService<WorkHistory, Long> {


    Collection<WorkHistory> getByWorkerId(Long workerId);

    void layOffWorker(WorkHistory wh, Long whId);


    void createNewWorkHistory(Long workerId, LocalDate startWork, Long emplPosId);


    List<WorkHistory> getCurrentPosition(Worker worker);
}


