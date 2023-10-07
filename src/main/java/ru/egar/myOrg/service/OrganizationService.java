package ru.egar.myOrg.service;

import ru.egar.myOrg.organization.Organization;

import java.util.List;

public interface OrganizationService {
    Organization create();

    Organization get();

    List<Organization> getAll();

    void delete();
}
