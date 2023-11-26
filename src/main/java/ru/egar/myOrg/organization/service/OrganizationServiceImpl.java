package ru.egar.myOrg.organization.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.organization.mapper.OrganizationMapper;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.organization.repository.OrganizationRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;


    @Override
    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll()
                .stream()
                .map(OrganizationMapper::toOrganizationDto)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "organizathion")
    @Override
    public Optional<OrganizationDto> getById(Long aLong) {
        final Optional<OrganizationDto> orgDto = organizationRepository.findById(aLong).
                map(OrganizationMapper::toOrganizationDto);
        return orgDto;
    }

    @CacheEvict(cacheNames = "organizathion", allEntries = true)
    @Override
    public OrganizationDto create(OrganizationDto dto) {
        return OrganizationMapper.toOrganizationDto(
                organizationRepository.save(
                        OrganizationMapper.toOrganization(dto)));
    }

    @CacheEvict(cacheNames = "organizathion", allEntries = true)
    @Override
    public OrganizationDto updateById(Long aLong, OrganizationDto organization) {
        organization.setId(aLong);
        return OrganizationMapper.toOrganizationDto(organizationRepository.save(OrganizationMapper.toOrganization(organization)));
    }

    @CacheEvict(cacheNames = "organizathion", allEntries = true)
    @Override
    public void deleteById(Long aLong) {
        organizationRepository.deleteById(aLong);
    }


    @Override
    public Collection<Organization> getAllSentBirthday() {
        return organizationRepository.getAllBySupportOrg_SendEmailBirthday(true);
    }
}
