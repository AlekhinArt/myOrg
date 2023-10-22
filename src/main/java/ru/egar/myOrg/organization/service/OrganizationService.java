package ru.egar.myOrg.organization.service;

import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.worker.model.Worker;

import java.util.List;


public interface OrganizationService  extends BaseService <OrganizationDto, Long> {
    List <Worker> getWorkers(Long id);

}
