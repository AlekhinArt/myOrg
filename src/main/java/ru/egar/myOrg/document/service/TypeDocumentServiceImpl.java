package ru.egar.myOrg.document.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.document.model.TypeDocument;
import ru.egar.myOrg.document.repository.TypeDocumentRepository;
import ru.egar.myOrg.exception.NotFoundException;

@Service
@AllArgsConstructor
public class TypeDocumentServiceImpl implements TypeDocumentService {
    private final TypeDocumentRepository tdr;

    @Override
    public TypeDocument getById(Long id) {
        return tdr.findById(id).orElseThrow(() -> new NotFoundException("Документ не найден"));
    }
}
