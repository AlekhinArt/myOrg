package ru.egar.myOrg.worker.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egar.myOrg.worker.model.Worker;

import java.util.Collection;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {


    @Query
    Collection<Worker> getWorkerByOrganization_Id(Long Id);

    @Query(value = "select w.* " +
            "from worker w " +
            "left join work_history wh ON w.id =wh.worker_id " +
            "left join employ_pos ep on wh.empl_id  = ep.id " +
            "where (w.delete =false " +
            "and w.work_now = :workNow " +
            "and w.org_id = :orgId )  " +
            "and ( ep.position ilike %:word% " +
            "or w.gender ilike  %:word% " +
            "or w.name ilike %:word% " +
            "or w.surname  ilike %:word% " +
            "or w.patronymic ilike %:word% " +
            "or w.phone_number like %:word%) " +
            "group by w.id  "
            , nativeQuery = true)
    Collection<Worker> searchWorkerByParam(@Param("orgId") long id, @Param("word") String word, @Param("workNow") Boolean workNow);

    @Query(value = "select * " +
            "from worker w  " +
            "where w.org_id in :orgs and " +
            "DATE_PART('day', w.birthday) = date_part('day', CURRENT_DATE)" +
            "AND " +
            "    DATE_PART('month', w.birthday) = date_part('month', CURRENT_DATE)", nativeQuery = true)
    Collection<Worker> findAllByOrganizationInAndBirthday(Collection<Long> orgs);
    @Cacheable(cacheNames = "worker")
    Optional<Worker> findById(Long id);


}
