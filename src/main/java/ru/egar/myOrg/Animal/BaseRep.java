package ru.egar.myOrg.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseRep<T extends Animal> extends JpaRepository<T, Long> {

    List<T> findAll(Class<T> g);
}
