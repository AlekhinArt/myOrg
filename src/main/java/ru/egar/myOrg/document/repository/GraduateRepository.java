package ru.egar.myOrg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.document.model.Graduate;

public interface GraduateRepository extends JpaRepository<Graduate, Long> {

}
