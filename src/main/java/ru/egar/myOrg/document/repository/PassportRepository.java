package ru.egar.myOrg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.document.model.Passport;

public interface PassportRepository extends JpaRepository <Passport, Long> {
}
