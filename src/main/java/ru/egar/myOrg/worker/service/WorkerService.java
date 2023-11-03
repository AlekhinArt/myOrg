package ru.egar.myOrg.worker.service;

import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.model.Worker;

import java.util.List;

public interface WorkerService extends BaseService <WorkerDto, Long> {
    WorkerDto create(WorkerCreateDto workerDto);




}
