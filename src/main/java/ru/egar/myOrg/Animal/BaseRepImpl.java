package ru.egar.myOrg.Animal;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BaseRepImpl<Type extends Animal> implements BaseRep<Type> {
    private final EntityManager em;

    @Override
    public List<Type> find(Class<Type> type) {
        return em.createQuery("from " + type.getName(), type)
                .getResultList();
    }
}
