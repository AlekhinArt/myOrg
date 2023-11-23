package ru.egar.myOrg.worker.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.mapper.EmployPositionMapper;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.repository.WorkHistoryRepository;
import ru.egar.myOrg.worker.repository.WorkerRepository;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkHistoryService;
import ru.egar.myOrg.worker.service.WorkerService;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class WorkerHistoryService implements WorkHistoryService {
    private final WorkHistoryRepository workHistoryRepository;
    private final WorkerRepository wr;
    private final EmployPositionService emps;


    @Override
    public List<WorkHistory> getAll() {
        return workHistoryRepository.findAll();
    }

    @Override
    public Optional<WorkHistory> getById(Long aLong) {
        return workHistoryRepository.findById(aLong);
    }

    @Override
    public WorkHistory create(WorkHistory wh) {
        return workHistoryRepository.save(wh);
    }

    @Override
    public WorkHistory updateById(Long aLong, WorkHistory workHistory) {

        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        workHistoryRepository.deleteById(aLong);

    }

    @Override
    public Collection<WorkHistory> getByWorkerId(Long workerId) {
        return workHistoryRepository.findAllByWorkerId(workerId);
    }

    @Override
    public void layOffWorker(WorkHistory wh, Long whId) {
        WorkHistory whOld = workHistoryRepository.findById(whId).orElseThrow(() -> new NotFoundException("История не найдена"));
        whOld.setEndWork(wh.getEndWork());
        whOld.setWorkNow(false);
        workHistoryRepository.save(whOld);
        Boolean workNow;
        Worker worker = whOld.getWorker();
        if (workHistoryRepository.findAllByWorkerAndWorkNow(whOld.getWorker(), true).size() < 1) {
            worker.setWorkNow(false);
            wr.save(worker);
        }

    }


    @CacheEvict(value = "worker", allEntries = true)
    @Override
    public void createNewWorkHistory(Long workerId, LocalDate startWork, Long emplPosId) {
        Worker worker = wr.findById(workerId).orElseThrow(()->new NotFoundException("Работник не найден"));
        workHistoryRepository.save(WorkHistory.builder()
                .workNow(true)
                .startWork(startWork)
                .worker(worker)
                .employPosition(emps.getAll().stream()
                        .filter(pos -> Objects.equals(pos.getId(), emplPosId))
                        .map(EmployPositionMapper::toEmployPosition)
                        .findFirst().orElseThrow(() -> new NotFoundException("Позиция не найдена")))
                .build());
            worker.setWorkNow(true);
            wr.save(worker);

    }

    @Override
    public List<WorkHistory> getCurrentPosition(Worker worker) {
        return workHistoryRepository.findAllByWorkerAndWorkNow(worker, Boolean.TRUE);
    }
}
