package ru.egar.myOrg.worker.service.impl;

import org.springframework.stereotype.Service;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.repository.WorkHistoryRepository;
import ru.egar.myOrg.worker.service.WorkHistoryService;

import java.util.List;
import java.util.Optional;


@Service
public class WorkerHistoryService implements WorkHistoryService {
    private final WorkHistoryRepository workHistoryRepository;

    public WorkerHistoryService(WorkHistoryRepository workHistoryRepository) {
        this.workHistoryRepository = workHistoryRepository;
    }

    @Override
    public List<WorkHistory> getAll() {
        return workHistoryRepository.findAll();
    }

    @Override
    public Optional<WorkHistory> getById(Long aLong) {
        return workHistoryRepository.findById(aLong);
    }

    @Override
    public WorkHistory create(WorkHistory dto) {
        return workHistoryRepository.save(dto);

    }

    @Override
    public WorkHistory updateById(Long aLong, WorkHistory workHistory) {

        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        workHistoryRepository.deleteById(aLong);

    }

}
