package ru.egar.myOrg.worker.model.notWorksDays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SickDays extends NotWorksDays{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
