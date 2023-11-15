package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.worker.model.Worker;

import java.util.Collection;

public interface WorkerRepository extends JpaRepository<Worker, Long> {


    @Query
    Collection <Worker> getWorkerByOrganization_Id(Long Id);
}
