package ru.egar.myOrg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.document.model.PaperDocument;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<PaperDocument, Long> {
    Optional<PaperDocument> findByWorkerIdAndActualTrue(Long id);

}
