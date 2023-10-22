package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.worker.model.WorkHistory;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {
}
