package ru.egar.myOrg.organization.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.organization.mapper.OrganizationMapper;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.Animal.BaseRep;
import ru.egar.myOrg.organization.repository.OrganizationRepository;
import ru.egar.myOrg.organization.service.OrganizationService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationMapper orgMap;
    private final OrganizationRepository organizationRepository;



    @Override
    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll()
                .stream()
                .map(orgMap::toOrganizationDto)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<OrganizationDto> getById(Long aLong) {
        final Optional<OrganizationDto> orgDto = organizationRepository.findById(aLong).
                map(orgMap::toOrganizationDto);
        return orgDto;
    }


    @Override
    public OrganizationDto create(OrganizationDto dto) {
        return saveOrgByDto(dto);
    }


    @Override
    public OrganizationDto updateById(Long aLong, OrganizationDto organizationDto) {
        organizationDto.setId(aLong);
        return saveOrgByDto(organizationDto);
    }


    @Override
    public void deleteById(Long aLong) {
        organizationRepository.deleteById(aLong);
    }


    @Override
    public Collection<Organization> getAllSentBirthday() {
        return organizationRepository.getAllBySupportOrg_SendEmailBirthday(true);
    }

    public OrganizationDto saveOrgByDto(OrganizationDto orgDto) {
        var organization = orgMap.toOrganization(orgDto);
        organization = organizationRepository.save(organization);
        return orgMap.toOrganizationDto(organization);
    }
}
