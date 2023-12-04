package ru.egar.myOrg.worker.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.organization.mapper.OrganizationMapper;
import ru.egar.myOrg.organization.service.OrganizationService;
import ru.egar.myOrg.worker.model.ValuableObject;
import ru.egar.myOrg.worker.repository.ValuableObjectsRepository;
import ru.egar.myOrg.worker.service.ValuableObjectsService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ValuableObjectServiceImpl implements ValuableObjectsService {
    private final OrganizationMapper orgMap;
    private final ValuableObjectsRepository vorRep;
    private final OrganizationService orgSer;

    @Override
    public List<ValuableObject> getAll() {
        return null;
    }

    @Override
    public Optional<ValuableObject> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public ValuableObject create(ValuableObject vo) {
        vorRep.save(vo);
        return null;
    }

    @Override
    public ValuableObject updateById(Long aLong, ValuableObject valuableObject) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public ValuableObject create(ValuableObject vo, Long orgId) {
        vo.setOrganization(orgSer.getById(orgId)
                .map(orgMap::toOrganization)
                .orElseThrow(() -> new NotFoundException("Организация не найдена")));

        return vorRep.save(vo);
    }

    @Override
    public Collection<ValuableObject> getAllByOrgId(Long orgId) {

        return vorRep.findAllByOrganization_Id(orgId);

    }

    @Override
    public Collection<ValuableObject> searchBy(Long orgId, String word, String type) {

        return vorRep.searchValuableObjectByByOrgAndParam(orgId, word);
    }
}
