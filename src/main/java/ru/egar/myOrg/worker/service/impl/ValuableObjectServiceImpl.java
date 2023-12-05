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

@Service
@AllArgsConstructor
public class ValuableObjectServiceImpl implements ValuableObjectsService {
    private final OrganizationMapper orgMap;
    private final ValuableObjectsRepository voRepo;
    private final OrganizationService orgService;


    @Override
    public ValuableObject create(ValuableObject vo) {
        return voRepo.save(vo);
    }

    @Override
    public void deleteById(Long aLong) {
        voRepo.deleteById(aLong);

    }

    @Override
    public ValuableObject create(ValuableObject vo, Long orgId) {
        vo.setOrganization(orgService.getById(orgId)
                .map(orgMap::toOrganization)
                .orElseThrow(() -> new NotFoundException("Организация не найдена")));
        return voRepo.save(vo);
    }

    @Override
    public Collection<ValuableObject> getAllByOrgId(Long orgId) {
        return voRepo.findAllByOrganization_Id(orgId);

    }

    @Override
    public Collection<ValuableObject> searchBy(Long orgId, String word, String type) {

        return voRepo.searchValuableObjectByByOrgAndParam(orgId, word);
    }
}
