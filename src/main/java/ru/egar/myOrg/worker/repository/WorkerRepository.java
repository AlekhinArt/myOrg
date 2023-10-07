package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.worker.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {



}
