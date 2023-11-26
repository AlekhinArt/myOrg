package ru.egar.myOrg.organization.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.organization.model.Organization;

import java.util.Collection;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Collection<Organization> getAllBySupportOrg_SendEmailBirthday(Boolean tr);
}
