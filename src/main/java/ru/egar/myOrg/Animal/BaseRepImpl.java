package ru.egar.myOrg.Animal;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class BaseRepImpl<Type extends Animal> implements BaseRep<Type> {

    @SuppressWarnings("rawtypes")
    private final Map<Class<? extends Animal>, SimpleJpaRepository> jpaRepositoryMap;

    @Autowired
    public BaseRepImpl(EntityManager em) {
        jpaRepositoryMap = new HashMap<>() {{
            put(Dog.class, new SimpleJpaRepository<Dog, UUID>(Dog.class, em));
            put(Cat.class, new SimpleJpaRepository<Cat, UUID>(Cat.class, em));
        }};
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Type> find(Class<Type> type) {
        return jpaRepositoryMap.get(type).findAll();
    }
}
