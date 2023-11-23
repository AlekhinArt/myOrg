package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egar.myOrg.worker.model.Worker;

import java.util.Collection;

public interface WorkerRepository extends JpaRepository<Worker, Long> {


    @Query
    Collection<Worker> getWorkerByOrganization_Id(Long Id);

    @Query(value = "select * " +
            "from Worker w " +
            "where (w.name ilike %:word% or w.phone_number ilike %:word% " +
            "or w.patronymic ilike %:word% " +
            "or w.surname ilike %:word%) and w.org_id = :orgId and w.delete = FALSE", nativeQuery = true)
    Collection<Worker> searchWorkerByOrgAndParam(@Param("orgId") long id, @Param("word") String word);


    @Query(value = "select w.* " +
            "from worker w " +
            "left join work_history wh ON w.id =wh.worker_id " +
            "left join employ_pos ep on wh.empl_id  = ep.id " +
            "where (w.delete =false " +
            "and wh.work_now = :workNow " +
            "and w.org_id = :orgId )  " +
            "and ( ep.position ilike %:word% " +
            "or w.gender ilike  %:word% " +
            "or w.name ilike %:word% " +
            "or w.surname  ilike %:word% " +
            "or w.patronymic ilike %:word% " +
            "or w.phone_number like %:word%) "
            , nativeQuery = true)
    Collection<Worker> searchWorkerByParam(@Param("orgId") long id, @Param("word") String word, @Param("workNow") Boolean workNow);
}
