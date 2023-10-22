package ru.egar.myOrg.worker.service.impl;

import org.springframework.stereotype.Service;
import ru.egar.myOrg.worker.model.ValuableObject;
import ru.egar.myOrg.worker.service.ValuableObjectsService;

import java.util.List;
import java.util.Optional;
@Service
public class ValuableObjectServiceImpl implements ValuableObjectsService {


    @Override
    public List<ValuableObject> getAll() {
        return null;
    }

    @Override
    public Optional<ValuableObject> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public ValuableObject create(ValuableObject dto) {
        return null;
    }

    @Override
    public ValuableObject updateById(Long aLong, ValuableObject valuableObject) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
