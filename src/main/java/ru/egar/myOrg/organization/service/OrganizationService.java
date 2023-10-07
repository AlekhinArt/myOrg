package ru.egar.myOrg.organization.service;

import ru.egar.myOrg.organization.model.Organization;

import java.util.List;

public interface OrganizationService {
    Organization create();

    Organization get();

    List<Organization> getAll();

    void delete();
}
