package ru.egar.myOrg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.worker.WorkHistory;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Integer> {
}
