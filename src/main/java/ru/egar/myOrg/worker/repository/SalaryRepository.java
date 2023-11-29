package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.worker.model.salary.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
