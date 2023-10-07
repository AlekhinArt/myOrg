package ru.egar.myOrg.worker.service;

import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.mapper.WorkerMapper;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.repository.WorkerRepository;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public WorkerDto create(WorkerDto workerDto) {
        return WorkerMapper.toWorkerDto(workerRepository.save(WorkerMapper.toWorker(workerDto)));

    }

    @Override
    public WorkerDto get(int id) {
        return WorkerMapper.toWorkerDto(workerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found")));
    }

    @Override
    public List<Worker> getAll() {

        return null;
    }

    @Override
    public void delete(int id) {
        workerRepository.deleteById(id);

    }
}
