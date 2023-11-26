package ru.egar.myOrg.worker.service;

import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.document.model.PaperDocument;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.dto.WorkerShowDto;
import ru.egar.myOrg.worker.model.Worker;

import java.util.Collection;

public interface WorkerService extends BaseService<WorkerDto, Long> {
    WorkerDto create(WorkerCreateDto workerDto, PaperDocument paperDocument);

    Collection<WorkerShowDto> showWorkers(Long orgId);

    Collection<WorkerShowDto> searchWorkers(Long orgId, String word, String workNow);

    Worker createWorker(Worker worker);


}
