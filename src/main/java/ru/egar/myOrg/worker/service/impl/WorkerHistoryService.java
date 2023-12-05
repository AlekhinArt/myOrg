package ru.egar.myOrg.worker.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.mapper.EmployPositionMapper;
import ru.egar.myOrg.worker.mapper.NotWorksDaysMapper;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.WorkTableInfo;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDaysWithDaysList;
import ru.egar.myOrg.worker.model.salary.Salary;
import ru.egar.myOrg.worker.model.salary.SalaryShow;
import ru.egar.myOrg.worker.repository.SalaryRepository;
import ru.egar.myOrg.worker.repository.WorkHistoryRepository;
import ru.egar.myOrg.worker.repository.WorkerRepository;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkHistoryService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WorkerHistoryService implements WorkHistoryService {
    private final EmployPositionMapper epMap;
    private final WorkHistoryRepository workHistoryRepository;
    private final WorkerRepository workerRepo;
    private final EmployPositionService empService;
    private final NotWorkDayServiceImpl nwdService;
    private final SalaryRepository salaryRepo;
    private final NotWorksDaysMapper mwdMap;


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
    public void createNewWorkHistory(Long workerId, LocalDate startWork, Long emplPosId, String baseRate, String indexRate) {
        Worker worker = workerRepo.findById(workerId).orElseThrow(() -> new NotFoundException("Работник не найден"));
        Salary salary = salaryRepo.save(Salary.builder()
                .baseRate(Double.valueOf(baseRate))
                .indexRate(Double.valueOf(indexRate))
                .build());
        workHistoryRepository.save(WorkHistory.builder()
                .workNow(true)
                .startWork(startWork)
                .worker(worker)
                .employPosition(empService.getAll().stream()
                        .filter(pos -> Objects.equals(pos.getId(), emplPosId))
                        .map(epMap::toEmployPosition)
                        .findFirst().orElseThrow(() -> new NotFoundException("Позиция не найдена")))
                .salary(salary)
                .build());
        worker.setWorkNow(true);
        workerRepo.save(worker);

    }

    @Override
    public void changeWorkerStatus(Long workerId) {
        if (workHistoryRepository.findAllByWorkerIdAndWorkNow(workerId, true).size() < 1) {
            Worker worker = workerRepo.findById(workerId).orElseThrow(() -> new NotFoundException("Работник не найден"));
            worker.setWorkNow(false);
            workerRepo.save(worker);
        }
    }

    //    @Cacheable(cacheNames = "table")
    @Override
    public WorkTableInfo[][] getNotWorksDayInCalendar(Long whId, String startPeriod, String endPeriod) {
        log.info("начал гиблое дело");
        WorkHistory whById = getById(whId).orElseThrow(() -> new NotFoundException("История не найдена"));
        LocalDate startWh = whById.getStartWork();
        LocalDate endWh;
        if (whById.getEndWork() == null) {
            endWh = LocalDate.MAX;
        } else endWh = whById.getEndWork();

        List<NotWorksDaysWithDaysList> nwdsList = nwdService.notWorkDayByTypeAndDate("", whId, startPeriod, endPeriod)
                .stream()
                .map(mwdMap::toList)
                .collect(Collectors.toList());
        LocalDate localDate = LocalDate.parse(startPeriod);

        return addToCalendar(getCalendar(localDate.minusDays(localDate.getDayOfMonth() - 1L)),
                nwdsList, startWh, endWh, LocalDate.parse(startPeriod));

    }


    @Override
    public SalaryShow getPaymentInfo(Long whId, String start, String end) {
        int sumHours = 0;
        int sumWorkHours = 0;
        double pay = 0;
        WorkTableInfo[][] nwdcal = getNotWorksDayInCalendar(whId, start, end);
        for (WorkTableInfo[] workTableInfos : nwdcal) {
            for (int j = 0; j < nwdcal[0].length; j++) {
                if (workTableInfos[j].getHours() != null) {
                    sumHours = sumHours + workTableInfos[j].getHours();
                    if (workTableInfos[j].isWorkDay()) {
                        sumWorkHours = sumWorkHours + 8;
                    }
                }

            }
        }
        WorkHistory workHistory = getById(whId).orElseThrow(() -> new NotFoundException("История не найдена"));
        if (sumHours > 0)
            pay = ((workHistory.getSalary().getBaseRate() * workHistory.getSalary().getIndexRate()) * ((double) sumHours / (double) sumWorkHours));

        clearCash();

        return SalaryShow.builder()
                .allWorkHours(sumWorkHours)
                .sumHours(sumHours)
                .indexRate(workHistory.getSalary().getIndexRate())
                .baseRate(workHistory.getSalary().getBaseRate())
                .sumMoney(String.format("%.2f", pay))
                .build();
    }

    //    @CacheEvict(cacheNames = "table", allEntries = true)
    public void clearCash() {

    }

    //метод создания календаря
    private WorkTableInfo[][] getCalendar(LocalDate firstDayMonth) {
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
                            .workDay(false)
                            .whereBe("ВЫХОДНОЙ")
                            .build();
                    ++date;

                } else if (((firstDayMonth.getDayOfWeek().getValue() - 1 <= j && j <= 5) || i > 0) && (date <= daysInMonth)) {
                    tableInfos[i][j] = WorkTableInfo
                            .builder()
                            .dateMonth(date)
                            .show(true)
                            .workDay(true)
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


    //Добавляем в календарь не рабочие дни и проверяем на принадлежность к истории
    private WorkTableInfo[][] addToCalendar(WorkTableInfo[][] calendar,
                                            List<NotWorksDaysWithDaysList> nwds,
                                            LocalDate startWh, LocalDate endWh, LocalDate startPeriod) {


        log.info("list size {}", nwds.size());
        LocalDate startPer = startPeriod;
        log.info("ONE startWH {}, startPer {}, endWh {}", startWh, startPer, endWh);
        for (int i = 0; i < calendar.length; i++) {
            for (int j = 0; j < calendar[0].length; j++) {
                log.info("{}", calendar[i][j]);
                //точка для отсчета
                if (calendar[i][j].getDateMonth() > 0) {
                    startPer = startPer.plusDays(1L);
                    WorkTableInfo wti = calendar[i][j];
                    if (startPer.isAfter(startWh) && startPer.isBefore(endWh)) {
                        log.info("wti SET HOURS date {}, i = {}, j ={}", calendar[i][j], i, j);
                        if (j != 5 && j != 6) {
                            wti.setHours(8);
                        }
                        for (NotWorksDaysWithDaysList nwd : nwds) {
                            for (LocalDate notWorksDay : nwd.getNotWorksDays()) {
                                if (calendar[i][j].getDateMonth() == notWorksDay.getDayOfMonth()) {
                                    log.info("{} = {}, {}, type {} ", calendar[i][j].getDateMonth(), notWorksDay.getDayOfMonth(), nwd.getNwdId(), nwd.getTypeDay().getType());
                                    wti = calendar[i][j];
                                    wti.setHours(0);
                                    wti.setWhereBe("Не был на работе(" + nwd.getTypeDay().getType() + ")");
                                    calendar[i][j] = wti;
                                }
                            }
                        }
                    } else wti.setWhereBe("НЕ В ДОЛЖНОСТИ");
                }
            }
        }
        return calendar;


    }


}
