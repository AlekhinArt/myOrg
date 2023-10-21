package ru.egar.myOrg.worker.service;

import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.mapper.WorkerMapper;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void deleteById(Long aLong) {
        workerRepository.deleteById(aLong);
    }


    @Override
    public List<WorkerDto> getAll() {
        return workerRepository.findAll().stream()
                .map(WorkerMapper::toWorkerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<WorkerDto> getById(Long aLong) {
        return workerRepository.findById(aLong)
                .map(WorkerMapper::toWorkerDto);
    }

    @Override
    public WorkerDto updateById(Long aLong, WorkerDto workerDto) {
        return null;
    }


}
