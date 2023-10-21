package ru.egar.myOrg.base;

import java.util.List;
import java.util.Optional;

public interface BaseService<E, Id> {

    List<E> getAll();

    Optional<E> getById(Id id);

    E create(E dto);

    E updateById(Id id, E e);

    void deleteById(Id id);

}
