package ru.egar.myOrg.document.service;

import ru.egar.myOrg.document.model.TypeDocument;

import java.util.List;

public interface TypeDocumentService {
    TypeDocument getById(Long id);

    List<TypeDocument> getAllByIdentity(Boolean identity);
}
