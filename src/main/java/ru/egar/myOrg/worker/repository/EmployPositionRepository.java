package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.worker.model.EmployPosition;

public interface EmployPositionRepository extends JpaRepository<EmployPosition, Long> {
}
