package ru.egar.myOrg.worker.service;

import ru.egar.myOrg.worker.model.ValuableObject;

import java.util.List;

public interface ValuableObjectsService {
    ValuableObject create();

    ValuableObject get();

    List<ValuableObject> getAll();

    void delete();
}
