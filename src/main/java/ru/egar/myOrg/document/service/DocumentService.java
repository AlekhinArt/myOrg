package ru.egar.myOrg.document.service;

import ru.egar.myOrg.document.model.PaperDocument;

import java.util.List;

public interface DocumentService {
    PaperDocument create();

    PaperDocument get();

    List<PaperDocument> getAll();

    void delete();


}
