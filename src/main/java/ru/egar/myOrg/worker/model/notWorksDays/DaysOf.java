package ru.egar.myOrg.worker.model.notWorksDays;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table (name = "days_of")
public class DaysOf extends NotWorksDays{

}
