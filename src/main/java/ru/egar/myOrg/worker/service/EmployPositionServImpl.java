package ru.egar.myOrg.worker.service;

import org.springframework.stereotype.Service;
import ru.egar.myOrg.worker.model.EmployPosition;
import ru.egar.myOrg.worker.repository.EmployPositionRepository;

import java.util.List;
@Service
public class EmployPositionServImpl implements EmployPositionService {

    public EmployPositionServImpl(EmployPositionRepository employPositionRepository) {
        this.employPositionRepository = employPositionRepository;
    }

    private final EmployPositionRepository employPositionRepository;

    @Override
    public EmployPosition create() {
        return null;
    }

    @Override
    public EmployPosition get() {
        return null;
    }

    @Override
    public List<EmployPosition> getAll() {
        return null;
    }

    @Override
    public void delete() {

    }
}
