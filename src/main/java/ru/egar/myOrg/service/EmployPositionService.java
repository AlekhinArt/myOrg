package ru.egar.myOrg.service;

import ru.egar.myOrg.worker.EmployPosition;

import java.util.List;

public interface EmployPositionService {

    EmployPosition create();

    EmployPosition get();

    List<EmployPosition> getAll();

    void delete();
}
