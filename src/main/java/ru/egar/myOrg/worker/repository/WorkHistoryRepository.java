package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.worker.model.WorkHistory;

import java.util.Collection;
import java.util.List;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {
    Collection<WorkHistory> findAllByWorkerId(Long workerId);

    List<WorkHistory> findAllByWorkerIdAndWorkNow(Long workerId, Boolean workNow);


}
