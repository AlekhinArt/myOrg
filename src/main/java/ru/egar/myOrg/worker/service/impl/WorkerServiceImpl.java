package ru.egar.myOrg.worker.service.impl;

import org.springframework.stereotype.Service;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.mapper.WorkerMapper;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.repository.EmployPositionRepository;
import ru.egar.myOrg.worker.repository.WorkHistoryRepository;
import ru.egar.myOrg.worker.repository.WorkerRepository;
import ru.egar.myOrg.worker.service.WorkerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final EmployPositionRepository emlpRepository;
    private final WorkHistoryRepository whr;


    public WorkerServiceImpl(WorkerRepository workerRepository, EmployPositionRepository emlpRepository,
                             WorkHistoryRepository whr) {
        this.workerRepository = workerRepository;
        this.emlpRepository = emlpRepository;
        this.whr = whr;
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

    @Override
    public WorkerDto create(WorkerCreateDto workerDto) {
        ArrayList<WorkHistory> wh = new ArrayList<>();
        Worker newWorker = Worker.builder()
                .name(workerDto.getName())
                .surname(workerDto.getSurname())
                .patronymic(workerDto.getPatronymic())
                .phoneNumber(workerDto.getPhoneNumber())
                .birthday(workerDto.getBirthday())
                .workNow(workerDto.getWorkNow())
                .build();
        wh.add(WorkHistory.builder()
                .workerH(newWorker)
                .startWork(workerDto.getStartWork())
                .workNow(workerDto.getWorkNow())
                .employPosition(emlpRepository.getByPosition(workerDto.getEmployPosition()))
                .build());
        newWorker.setWorkHistory(wh);
//        newWorker = workerRepository.save(newWorker);
        return WorkerMapper.toWorkerDto(workerRepository.save(newWorker));
    }

}
