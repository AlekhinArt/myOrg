package ru.egar.myOrg.Animal;

import java.util.List;

public interface AnimalGo<T extends Animal >{

    List<T> findAll ();
}
