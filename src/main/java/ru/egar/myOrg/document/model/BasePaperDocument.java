package ru.egar.myOrg.document.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.egar.myOrg.worker.model.Worker;

@Data
@MappedSuperclass

//Базовый класс документов
public abstract class BasePaperDocument {
    @NotNull
    private String typeDocument;
    @NotNull
    @ManyToOne
    private Worker worker;
    @NotNull
    private Boolean actual;

}
