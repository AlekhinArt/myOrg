package ru.egar.myOrg.worker.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.repository.NotWorksDaysRepository;
import ru.egar.myOrg.worker.service.NotWorksDayService;
import ru.egar.myOrg.worker.service.WorkerService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotWorksDayServiceImpl implements NotWorksDayService {

    private final NotWorksDaysRepository worksDaysRepository;
    private final WorkerService workerService;


    @Override
    public List<NotWorksDays> getAll() {
//        worksDaysRepository.findAll();
        return worksDaysRepository.findAll();
    }

    @Override
    public Optional<NotWorksDays> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public NotWorksDays create(NotWorksDays day) {
        return worksDaysRepository.save(day);
    }

    @Override
    public NotWorksDays updateById(Long aLong, NotWorksDays notWorksDays) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        worksDaysRepository.deleteById(aLong);

    }

    @Override
    public Collection<NotWorksDays> getAllByWhId(Long id) {
        return worksDaysRepository.findAllByWorkHistoryId(id);
    }

    @Override
    public Collection<NotWorksDays> getAllByWhIdAndType() {
        return null;
    }
}
