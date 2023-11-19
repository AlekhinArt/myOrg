package ru.egar.myOrg.document.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class BasePaperDocument {
    @Size(min = 4, max = 4)
    private String codeTypeDocument;
    @Size(max = 30)
    @NotNull
    @NotBlank
    private String nameDocument;


}
