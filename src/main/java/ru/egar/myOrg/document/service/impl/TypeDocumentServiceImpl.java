package ru.egar.myOrg.document.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.document.model.TypeDocument;
import ru.egar.myOrg.document.repository.TypeDocumentRepository;
import ru.egar.myOrg.document.service.TypeDocumentService;
import ru.egar.myOrg.exception.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeDocumentServiceImpl implements TypeDocumentService {
    private final TypeDocumentRepository typeDocumentRepository;

    @Override
    public TypeDocument getById(Long id) {
        return typeDocumentRepository.findById(id).orElseThrow(() -> new NotFoundException("Документ не найден"));
    }

    @Override
    public List<TypeDocument> getAllByIdentity(Boolean identity) {
        return typeDocumentRepository.findAllByIdentity(identity);
    }
}
