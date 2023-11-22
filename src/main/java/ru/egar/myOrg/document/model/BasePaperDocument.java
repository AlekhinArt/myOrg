package ru.egar.myOrg.document.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import ru.egar.myOrg.worker.model.Worker;
@Data
@MappedSuperclass
//Базовый класс документов
public abstract class BasePaperDocument {
    @ManyToOne
    @JoinColumn(name = "type_dock_id")
    private TypeDocument typeDocument;
    @NotNull
    @OneToOne
    private Worker worker;
    @NotNull
    private Boolean actual;

}
