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


}
