package ru.egar.myOrg.worker.service;


import ru.egar.myOrg.worker.model.WorkHistory;

import java.util.List;

public interface WorkHistoryService {
    WorkHistory create();

    WorkHistory get();

    List<WorkHistory> getAll();

    void delete();
}
