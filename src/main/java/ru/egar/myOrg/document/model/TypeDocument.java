package ru.egar.myOrg.document.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "type_document", uniqueConstraints = {
        @UniqueConstraint(columnNames = "type_dock_id"),
        @UniqueConstraint(columnNames = "code_type_document")
})
//Справочник документов
public class TypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_dock_id")
    private Long id;
    @Size(min = 4, max = 4)
    @Column(name = "code_type_document")
    private String codeTypeDocument;
    @Size(max = 30)
    @NotNull
    @NotBlank
    @Column(name = "name_document")
    private String nameDocument;
    @NotNull
    private Boolean actual;
}
