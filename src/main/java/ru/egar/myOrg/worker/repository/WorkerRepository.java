package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.worker.model.Worker;

import java.util.Collection;

public interface WorkerRepository extends JpaRepository<Worker, Long> {


//    @Query(value = "select * from worker w " +
//            "left join WORKER_WORK_HISTORY WWH on w.worker_id = WWH.WORKER_ID " +
//            "left join WORK_HISTORY WH on WH.WORK_HISTORY_ID = WWH.WORK_HISTORY_ID " +
//            "left join EMPLOY_POS EP on WH.EMPL_ID = EP.EMPL_ID  " +
//            "where WH.WORK_NOW = TRUE", nativeQuery = true)

    @Query(value = "select w.WORKER_ID as wid, w.WORK_NOW, w.NAME, w.SURNAME, w.PATRONYMIC, w.BIRTHDAY, w.PHONE_NUMBER," +
            " ep.POSITION , WH.WORK_HISTORY_ID as whid " +
            "from worker w " +
            "left join WORKER_WORK_HISTORY WWH on w.worker_id  = WWH.WORKER_ID " +
            "left join WORK_HISTORY WH on WH.WORK_HISTORY_ID = WWH.WORK_HISTORY_ID " +
            "left join EMPLOY_POS EP on WH.EMPL_ID = EP.EMPL_ID " +
            "where WH.WORK_NOW = TRUE", nativeQuery = true)

//    @Query("SELECT w from Worker w where WorkHistory.workNow = true ")

        //    @Query(value = "select w from  Worker w left join WorkHistory wh on w.workHistory = wh.id.  left join EmployPosition ep on wh.employPosition.id = ep.id where wh.workNow = true ")
//   @Query("SELECT Worker from Worker w left join  WorkHistory wh on w.workHistory=(select )  ")
//    @Query("SELECT w from Worker w where w.workHistory=(select wh from WorkHistory wh )")
    Collection<Worker> showWorkerInMain();
}
