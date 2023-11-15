package ru.egar.myOrg.worker.service.impl;

import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.model.notWorksDays.TypeOffDay;
import ru.egar.myOrg.worker.repository.NotWorksDaysRepository;
import ru.egar.myOrg.worker.repository.WorkHistoryRepository;
import ru.egar.myOrg.worker.service.WorkHistoryService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class WorkerHistoryService implements WorkHistoryService {
    private final WorkHistoryRepository workHistoryRepository;
    private final NotWorksDaysRepository notWorksDaysRepository;

    public WorkerHistoryService(WorkHistoryRepository workHistoryRepository, NotWorksDaysRepository notWorksDaysRepository) {
        this.workHistoryRepository = workHistoryRepository;
        this.notWorksDaysRepository = notWorksDaysRepository;
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

    @Override
    public WorkHistory saveNotWorksDay(NotWorksDays nwd, Long whId) {
        WorkHistory wh = workHistoryRepository.findById(whId).orElseThrow(() -> new NotFoundException("История не найдена"));
        wh.getNotWorksDays().add(nwd);
        nwd.setWorkHistory(wh);
        notWorksDaysRepository.save(nwd);
        return workHistoryRepository.save(wh);
    }

    @Override
    public void layOffWorker(WorkHistory wh, Long whId) {
        WorkHistory whOld = workHistoryRepository.findById(whId).orElseThrow(() -> new NotFoundException("История не найдена"));
        whOld.setEndWork(wh.getEndWork());
        whOld.setWorkNow(false);
        workHistoryRepository.save(whOld);
    }

    @Override
    public Collection<NotWorksDays> notWorkDayByType(TypeOffDay type, Long whId) {


        return notWorksDaysRepository.findAllByWorkHistory_IdAndTypeDay(whId, type);
    }

}
