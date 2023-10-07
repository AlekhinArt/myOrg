package ru.egar.myOrg.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.organization.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
