package ru.egar.myOrg.service;

import ru.egar.myOrg.worker.Worker;

import java.util.List;

public interface WorkerService {
    Worker create();
    Worker get();
    List<Worker> getAll();
    void delete();

}
