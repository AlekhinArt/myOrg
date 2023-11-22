package ru.egar.myOrg.worker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.worker.model.enumerated.TypeOfValue;

import java.time.LocalDate;

;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ValuableObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obg_id")
    private Long id;
    @Column(name = "is_use")
    @NotNull
    private Boolean isUse;
    @Size(min = 1, max = 40, message = "Должно быть не меньше одного и не более 40 символов")
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private float price;
    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;
    @Column(name = "type_of_value")
    @Enumerated(EnumType.STRING)
    private TypeOfValue typeOfValue;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

}
