package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.worker.model.ValuableObject;


public interface ValuableObjectsRepository extends JpaRepository<ValuableObject, Integer> {
}
