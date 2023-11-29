package ru.egar.myOrg.worker.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.mapper.EmployPositionMapper;
import ru.egar.myOrg.worker.mapper.NotWorksDaysMapper;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.WorkTableInfo;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDaysWithDaysList;
import ru.egar.myOrg.worker.repository.WorkHistoryRepository;
import ru.egar.myOrg.worker.repository.WorkerRepository;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkHistoryService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WorkerHistoryService implements WorkHistoryService {
    private final WorkHistoryRepository workHistoryRepository;
    private final WorkerRepository wr;
    private final EmployPositionService emps;
    private final NotWorkDayServiceImpl nwds;


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
        log.info("worker id {} ", whOld.getWorker().getId());
        workHistoryRepository.save(whOld);
        changeWorkerStatus(whOld.getWorker().getId());
    }


    @CacheEvict(value = "worker", allEntries = true)
    @Override
    public void createNewWorkHistory(Long workerId, LocalDate startWork, Long emplPosId) {
        Worker worker = wr.findById(workerId).orElseThrow(() -> new NotFoundException("Работник не найден"));
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
    public void changeWorkerStatus(Long workerId) {
        if (workHistoryRepository.findAllByWorkerIdAndWorkNow(workerId, true).size() < 1) {
            Worker worker = wr.findById(workerId).orElseThrow(() -> new NotFoundException("Работник не найден"));
            worker.setWorkNow(false);
            wr.save(worker);
        }
    }
    @Cacheable(cacheNames = "table")
    @Override
    public WorkTableInfo[][] getNotWorksDayInCalendar(Long whId, String start, String end) {
        log.info("начал гиблое дело");
//        List<NotWorksDaysWithDaysList> nwdssad = getNotWorksDay(1L, "2023-11-01", "2023-11-30");

        List<NotWorksDaysWithDaysList> nwdsList = nwds.notWorkDayByTypeAndDate("", whId, start, end)
                .stream()
                .map(NotWorksDaysMapper::toList)
                .collect(Collectors.toList());
        LocalDate localDate = LocalDate.parse(start);

        return addToCalendar(getCalendar(  localDate.minusDays(localDate.getDayOfMonth()-1L)), nwdsList);

    }


    public void getWorkHours(Long whId, String start, String end){

    }

    private WorkTableInfo[][] getCalendar(LocalDate firstDayMonth) {
//        LocalDate localDate = LocalDate.parse("2023-11-01");
        log.info("День месяца {}", firstDayMonth.getDayOfWeek().getValue());
        int daysInMonth = (int) (firstDayMonth.plusMonths(1).toEpochDay() - firstDayMonth.toEpochDay());
        WorkTableInfo[][] tableInfos = new WorkTableInfo[6][7];
        int date = 1;

        for (int i = 0; i < tableInfos.length; i++) {
            for (int j = 0; j < tableInfos[0].length; j++) {
                //5 and 6 day of week
                if ((j == 5 || j == 6) && (date <= daysInMonth)) {

                    tableInfos[i][j] = WorkTableInfo
                            .builder()
                            .dateMonth(date)
                            .show(true)
                            .whereBe("ВЫХОДНОЙ")
                            .build();
                    ++date;

                } else if (((firstDayMonth.getDayOfWeek().getValue() - 1 <= j && j <= 5) || i > 0) && (date <= daysInMonth)) {
                    tableInfos[i][j] = WorkTableInfo
                            .builder()
                            .hours(8)
                            .dateMonth(date)
                            .show(true)
                            .build();
                    ++date;
                } else {
                    tableInfos[i][j] = WorkTableInfo
                            .builder()
                            .show(false)
                            .build();

                }


            }
        }
        return tableInfos;

    }


    private WorkTableInfo[][] addToCalendar(WorkTableInfo[][] calendar, List<NotWorksDaysWithDaysList> nwds) {
        log.info("list size {}", nwds.size());
        for (int i = 0; i < calendar.length; i++) {
            for (int j = 0; j < calendar[0].length; j++) {
                for (NotWorksDaysWithDaysList nwd : nwds) {
                    for (LocalDate notWorksDay : nwd.getNotWorksDays()) {

                        if (calendar[i][j].getDateMonth() == notWorksDay.getDayOfMonth()) {
                            log.info("{} = {}, {} ", calendar[0][j].getDateMonth(), notWorksDay.getDayOfMonth(), nwd.getNwdId());
                            log.info("тут был");
                            WorkTableInfo wti = calendar[i][j];
                            wti.setHours(0);
                            wti.setWhereBe("Не был на работе(" + nwd.getTypeDay().getType() + ")");
                            calendar[i][j] = wti;

                        }
                    }


                }

            }

        }
        return calendar;


    }


}
