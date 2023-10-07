package ru.egar.myOrg.service;

import ru.egar.myOrg.document.PaperDocument;

import java.util.List;

public interface DocumentService {
    PaperDocument create();

    PaperDocument get();

    List<PaperDocument> getAll();

    void delete();


}
