package ru.egar.myOrg.service;

import ru.egar.myOrg.worker.ValuableObject;

import java.util.List;

public interface ValuableObjectsService {
    ValuableObject create();

    ValuableObject get();

    List<ValuableObject> getAll();

    void delete();
}
