package ru.egar.myOrg.worker.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.organization.repository.OrganizationRepository;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.dto.WorkerShowDto;
import ru.egar.myOrg.worker.mapper.WorkerMapper;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.repository.EmployPositionRepository;
import ru.egar.myOrg.worker.repository.WorkHistoryRepository;
import ru.egar.myOrg.worker.repository.WorkerRepository;
import ru.egar.myOrg.worker.service.WorkerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final EmployPositionRepository emlpRepository;
    private final WorkHistoryRepository whr;
    private final OrganizationRepository orgRep;


    public WorkerServiceImpl(WorkerRepository workerRepository, EmployPositionRepository emlpRepository,
                             WorkHistoryRepository whr, OrganizationRepository orgRepl) {
        this.workerRepository = workerRepository;
        this.emlpRepository = emlpRepository;
        this.whr = whr;
        this.orgRep = orgRepl;
    }

    @Override
    public WorkerDto create(WorkerDto workerDto) {

        return WorkerMapper.toWorkerDto(workerRepository.save(WorkerMapper.toWorker(workerDto)));
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "workers", allEntries = true),
            @CacheEvict(cacheNames = "worker", allEntries = true)
    })
    @Override
    public void deleteById(Long aLong) {
        workerRepository.deleteById(aLong);
    }


    @Override
    public List<WorkerDto> getAll() {
        final List<WorkerDto> workerDTO = workerRepository.findAll().stream()
                .map(WorkerMapper::toWorkerDto)
                .collect(Collectors.toList());
        return workerDTO;
    }

    @Cacheable(cacheNames = "worker")
    @Override
    public Optional<WorkerDto> getById(Long aLong) {

        final var workerDto = workerRepository.findById(aLong)
                .map(WorkerMapper::toWorkerDto);
        return workerDto;


    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "workers", allEntries = true),
            @CacheEvict(cacheNames = "worker", allEntries = true)
    })

    @Override
    public WorkerDto updateById(Long aLong, WorkerDto workerDto) {
        return null;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "workers", allEntries = true),
            @CacheEvict(cacheNames = "worker", allEntries = true)
    })
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
                .organization(orgRep.findById(workerDto.getOrgId()).orElseThrow(
                        () -> new NotFoundException("Organization with id " +
                                workerDto.getOrgId() + " not found")))
                .build();
        wh.add(WorkHistory.builder()
                .worker(newWorker)
                .startWork(workerDto.getStartWork())
                .workNow(workerDto.getWorkNow())
                .employPosition(emlpRepository.getByPosition(workerDto.getEmployPosition()))
                .build());

        newWorker.setWorkHistory(wh);
        return WorkerMapper.toWorkerDto(workerRepository.save(newWorker));
    }

    @Cacheable(cacheNames = "workers")
    @Override
    public Collection<WorkerShowDto> showWorkers(Long id) {
        final List<WorkerShowDto> wsh = workerRepository.getWorkerByOrganization_Id(id)
                .stream()
                .map(WorkerMapper::toShowWorker)
                .collect(Collectors.toList());
        return wsh;
    }

    @Override
    public Collection<WorkerShowDto> searchWorkers(Long orgId, String word, String position) {
        if (position == null || position.isBlank()) {
            return workerRepository.searchWorkerByOrgAndParam(orgId, word)
                    .stream()
                    .map(WorkerMapper::toShowWorker)
                    .collect(Collectors.toList());
        } else {
            return workerRepository.searchWorkerByOrgAndParam(orgId, word)
                    .stream()
                    .map(WorkerMapper::toShowWorker)
                    .filter(pos -> pos.getEmployPosition().equals(position))
                    .collect(Collectors.toList());
        }

    }

}
