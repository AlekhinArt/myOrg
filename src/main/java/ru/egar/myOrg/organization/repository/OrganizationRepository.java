package ru.egar.myOrg.organization.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.organization.model.Organization;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {


}
