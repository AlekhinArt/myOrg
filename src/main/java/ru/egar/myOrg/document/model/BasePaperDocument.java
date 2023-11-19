package ru.egar.myOrg.document.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasePaperDocument {
    @Id
    Long id;
    String codeTypeDocument;
    String nameDocument;

}
