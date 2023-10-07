package ru.egar.myOrg.service;


import ru.egar.myOrg.worker.WorkHistory;

import java.util.List;

public interface WorkHistoryService {
    WorkHistory create();

    WorkHistory get();

    List<WorkHistory> getAll();

    void delete();
}
