package ru.egar.myOrg.Animal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalGoImpl implements AnimalGo{

    private final BaseRep baseRepCat;

    @Override
    public List<Cat> findAllCats() {
        return baseRepCat.find(Cat.class);
    }

    @Override
    public List<Dog> findAllDogs() {
        return baseRepCat.find(Dog.class);
    }
}
