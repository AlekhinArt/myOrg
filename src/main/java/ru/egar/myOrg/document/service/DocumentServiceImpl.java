package ru.egar.myOrg.document.service;


import ru.egar.myOrg.document.model.BasePaperDocument;

import java.util.List;
import java.util.Optional;

public class DocumentServiceImpl implements DocumentService {

    @Override
    public List<BasePaperDocument> getAll() {
        return null;
    }

    @Override
    public Optional<BasePaperDocument> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public BasePaperDocument create(BasePaperDocument dto) {
        return null;
    }

    @Override
    public BasePaperDocument updateById(Long aLong, BasePaperDocument basePaperDocument) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

}
