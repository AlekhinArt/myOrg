package ru.egar.myOrg.worker.model.notWorksDays;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "sick_days")
public class SickDays extends NotWorksDays {


}
