package ru.egar.myOrg.worker.model.notWorksDays;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "vacation")
public class Vacation extends NotWorksDays{

}
