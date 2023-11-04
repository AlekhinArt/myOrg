package ru.egar.myOrg.organization.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.organization.mapper.OrganizationMapper;
import ru.egar.myOrg.organization.repository.OrganizationRepository;
import ru.egar.myOrg.worker.model.Worker;

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

    @Override
    public Optional<OrganizationDto> getById(Long aLong) {
        return organizationRepository.findById(aLong).
                map(OrganizationMapper::toOrganizationDto);
    }

    @Override
    public OrganizationDto create(OrganizationDto dto) {
        return OrganizationMapper.toOrganizationDto(
                organizationRepository.save(
                        OrganizationMapper.toOrganization(dto)));
    }

    @Override
    public OrganizationDto updateById(Long aLong, OrganizationDto organization) {
        return OrganizationMapper.toOrganizationDto(organizationRepository.updateOrg(organization.getName(), organization.getInn(),
                organization.getOgrn(), organization.getAddress(),
                organization.getPhoneNumber(), aLong));
    }

    @Override
    public void deleteById(Long aLong) {
        organizationRepository.deleteById(aLong);
    }

    @Override
    public List<Worker> getWorkers(Long id) {
        return organizationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Organization with id " + id + " not found")).getWorkers();
    }
}
