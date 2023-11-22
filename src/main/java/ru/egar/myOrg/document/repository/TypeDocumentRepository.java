package ru.egar.myOrg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.document.model.TypeDocument;

public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
}
