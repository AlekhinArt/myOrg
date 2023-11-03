package ru.egar.myOrg.worker.model.notWorksDays;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor

@Entity
@Table (name = "days_of")
public class DaysOf extends NotWorksDays{

}
