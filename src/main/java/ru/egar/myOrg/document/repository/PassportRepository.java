package ru.egar.myOrg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.document.model.PaperDocument;

public interface PassportRepository extends JpaRepository<PaperDocument, Long> {
    PaperDocument findByWorkerIdAndActualTrue(Long id);

}
