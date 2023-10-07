package ru.egar.myOrg.worker.service;

import ru.egar.myOrg.worker.model.EmployPosition;

import java.util.List;

public interface EmployPositionService {

    EmployPosition create();

    EmployPosition get();

    List<EmployPosition> getAll();

    void delete();
}
