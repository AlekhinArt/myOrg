package ru.egar.myOrg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.organization.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
