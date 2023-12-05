package ru.egar.myOrg.worker.service;

import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.worker.model.ValuableObject;

import java.util.Collection;

public interface ValuableObjectsService  {

    ValuableObject create(ValuableObject vo);

    void deleteById(Long aLong);

    ValuableObject create(ValuableObject vo, Long orgId);

    Collection<ValuableObject> getAllByOrgId(Long orgId);

    Collection<ValuableObject> searchBy(Long orgId, String word, String type);
}
