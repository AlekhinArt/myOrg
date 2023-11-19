package ru.egar.myOrg.organization.mapper;

import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.organization.model.Organization;

public class OrganizationMapper {

    public static OrganizationDto toOrganizationDto(Organization organization) {
        return OrganizationDto.builder()
                .id(organization.getId())
                .address(organization.getAddress())
                .inn(organization.getInn())
                .ogrn(organization.getOgrn())
                .phoneNumber(organization.getPhoneNumber())
                .name(organization.getName())
                .zip(organization.getZip())
                .build();
    }

    public static Organization toOrganization(OrganizationDto organizationDto) {
        return Organization.builder()
                .id(organizationDto.getId())
                .address(organizationDto.getAddress())
                .inn(organizationDto.getInn())
                .ogrn(organizationDto.getOgrn())
                .phoneNumber(organizationDto.getPhoneNumber())
                .name(organizationDto.getName())
                .zip(organizationDto.getZip())
                .build();
    }

}
