package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.worker.model.EmployPosition;

import java.util.List;

public interface EmployPositionRepository extends JpaRepository<EmployPosition, Long> {

    @Query("SELECT position from EmployPosition ")
    List<String> getAllPosition();
}
