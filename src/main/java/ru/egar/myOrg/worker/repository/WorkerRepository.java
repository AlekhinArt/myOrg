package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.worker.model.Worker;

import java.util.Collection;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query(value = "select w.WORKER_ID as wid,  w.WORK_NOW, w.NAME, w.SURNAME, w.PATRONYMIC, w.BIRTHDAY, w.PHONE_NUMBER, w.ORG_ID," +
            " ep.POSITION , WH.WORK_HISTORY_ID as whid " +
            "from worker w " +
            "left join WORKER_WORK_HISTORY WWH on w.worker_id  = WWH.WORKER_ID " +
            "left join WORK_HISTORY WH on WH.WORK_HISTORY_ID = WWH.WORK_HISTORY_ID " +
            "left join EMPLOY_POS EP on WH.EMPL_ID = EP.EMPL_ID " +
            "where WH.WORK_NOW = TRUE", nativeQuery = true)
    Collection<Worker> showWorkersInMain();
}
