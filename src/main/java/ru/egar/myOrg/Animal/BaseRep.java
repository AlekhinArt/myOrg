package ru.egar.myOrg.Animal;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRep<T extends Animal> {

    List<T> find(Class<T> type);
}
