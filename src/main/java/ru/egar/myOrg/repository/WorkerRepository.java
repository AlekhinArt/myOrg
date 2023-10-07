package ru.egar.myOrg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.worker.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {



}
