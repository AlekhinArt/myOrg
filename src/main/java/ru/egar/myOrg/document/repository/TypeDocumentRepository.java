package ru.egar.myOrg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.document.model.TypeDocument;

import java.util.List;

public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
    List<TypeDocument> findAllByIdentity(Boolean identity);
}
