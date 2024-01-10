package ru.egar.myOrg.organization.service;

import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.organization.model.Organization;

import java.util.Collection;
import java.util.List;


public interface OrganizationService extends BaseService<OrganizationDto, Long> {

    Collection<Organization> getAllSentBirthday();


}
