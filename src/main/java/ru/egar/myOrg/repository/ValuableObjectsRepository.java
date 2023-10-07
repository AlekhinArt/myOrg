package ru.egar.myOrg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.worker.ValuableObject;


public interface ValuableObjectsRepository extends JpaRepository<ValuableObject, Integer> {
}
