package ru.egar.myOrg.worker.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.document.model.Passport;
import ru.egar.myOrg.document.service.DocumentService;
import ru.egar.myOrg.document.service.TypeDocumentService;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.organization.repository.OrganizationRepository;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.dto.WorkerShowDto;
import ru.egar.myOrg.worker.mapper.WorkerMapper;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.repository.EmployPositionRepository;
import ru.egar.myOrg.worker.repository.WorkerRepository;
import ru.egar.myOrg.worker.service.WorkHistoryService;
import ru.egar.myOrg.worker.service.WorkerService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final EmployPositionRepository emlpRepository;
    private final WorkHistoryService workHistoryService;
    private final OrganizationRepository orgRep;
    private final DocumentService ds;
    TypeDocumentService tds;

    @Caching(evict = {
            @CacheEvict(cacheNames = "workers", allEntries = true),
            @CacheEvict(cacheNames = "worker", allEntries = true)
    })
    @Override
    public WorkerDto create(WorkerDto workerDto) {
        workerDto.setOrganization(orgRep.findById(workerDto.getOrgId()).orElseThrow(() -> new NotFoundException("Организация не найдена")));
        return WorkerMapper.toWorkerDto(workerRepository.save(WorkerMapper.toWorker(workerDto)));
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "workers", allEntries = true),
            @CacheEvict(cacheNames = "worker", allEntries = true)
    })
    @Override
    public void deleteById(Long aLong) {
        Worker worker = workerRepository.findById(aLong).orElseThrow(() -> new NotFoundException("Работник не найден"));
        worker.setDelete(true);
        workerRepository.save(worker);

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
    public WorkerDto create(WorkerCreateDto workerDto, Passport passport) {
        Worker newWorker = workerRepository.save(Worker.builder()
                .name(workerDto.getName())
                .surname(workerDto.getSurname())
                .patronymic(workerDto.getPatronymic())
                .phoneNumber(workerDto.getPhoneNumber())
                .birthday(workerDto.getBirthday())
                .workNow(workerDto.getWorkNow())
                .familyStatus(workerDto.getFamilyStatus())
                .gender(workerDto.getGender())
                .delete(Boolean.FALSE)
                .minorChildren(workerDto.getMinorChildren())
                .organization(orgRep.findById(workerDto.getOrgId()).orElseThrow(
                        () -> new NotFoundException("Organization with id " +
                                workerDto.getOrgId() + " not found")))
                .build());
        log.info("worker id {}", newWorker.getId());
        workHistoryService.create(WorkHistory.builder()
                .worker(newWorker)
                .startWork(workerDto.getStartWork())
                .workNow(workerDto.getWorkNow())
                .employPosition(emlpRepository.getByPosition(workerDto.getEmployPosition()))
                .build());
        savePassport(newWorker, passport);
        return WorkerMapper.toWorkerDto(newWorker);
    }

    @Cacheable(cacheNames = "workers")
    @Override
    public List<WorkerShowDto> showWorkers(Long id) {
        final List<WorkerShowDto> wsh = workerRepository.getWorkerByOrganization_Id(id)
                .stream()
                .map(WorkerMapper::toShowWorker)
                .collect(Collectors.toList());
        return wsh;
    }


    // TODO: 20.11.2023 работает не корректно, переделать
    @Override
    public Collection<WorkerShowDto> searchWorkers(Long orgId, String word, String position, String workNow) {
        Collection<WorkerShowDto> workerShowDtos;
        if (position == null || position.isBlank()) {
            workerShowDtos = workerRepository.searchWorkerByOrgAndParam(orgId, word)
                    .stream()
                    .map(WorkerMapper::toShowWorker)
                    .collect(Collectors.toList());
        } else {
            workerShowDtos = workerRepository.searchWorkerByOrgAndParam(orgId, word)
                    .stream()
                    .map(WorkerMapper::toShowWorker)
                    .filter(pos -> pos.getEmployPosition().equals(position))
                    .collect(Collectors.toList());
        }
        if (!(workNow == null) && workNow.isBlank()) {
            return workerShowDtos;
        } else return workerShowDtos.stream()
                .filter(w -> w.getWorkNow().equals(Boolean.valueOf(workNow)))
                .collect(Collectors.toList());
    }


    @Override
    public Worker createWorker(Worker worker) {

        return workerRepository.save(worker);
    }

    private void savePassport(Worker worker, Passport passport) {
        passport.setWorker(worker);
        passport.setActual(true);
        passport.setTypeDocument(tds.getById(1L));
        ds.save(passport);

    }

    public void howLongTime() {
//        Worker worker;
//        worker.getWorkHistory();
    }

}
