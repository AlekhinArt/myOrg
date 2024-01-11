package ru.egar.myOrg.Animal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalGoImpl implements AnimalGo {

    private final BaseRep baseRepCat;

    @Override
    public List<Animal> findAllCats() {
        return baseRepCat.find(Cat.class);
    }

    @Override
    public List<Animal> findAllDogs() {
        return baseRepCat.find(Dog.class);
    }
}
