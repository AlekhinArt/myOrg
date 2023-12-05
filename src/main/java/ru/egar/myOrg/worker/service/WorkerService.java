package ru.egar.myOrg.worker.service;

import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.document.model.PaperDocument;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;
import ru.egar.myOrg.worker.dto.WorkerDto;

import java.util.Collection;

public interface WorkerService extends BaseService<WorkerDto, Long> {
    WorkerDto create(WorkerCreateDto workerDto, PaperDocument paperDocument);

    Collection<WorkerDto> showWorkers(Long orgId);

    Collection<WorkerDto> searchWorkers(Long orgId, String word, String workNow);




    Collection<WorkerDto> getForSendMail(Collection<Long> orgs);
}
