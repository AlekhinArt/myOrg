package ru.egar.myOrg.worker.service;

import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.model.Worker;

import java.util.List;

public interface WorkerService {
    WorkerDto create(WorkerDto workerDto);

    WorkerDto get(int id);

    List<Worker> getAll();

    void delete(int id);



}
