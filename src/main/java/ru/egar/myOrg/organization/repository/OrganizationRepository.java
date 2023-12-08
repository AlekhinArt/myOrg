package ru.egar.myOrg.organization.repository;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.egar.myOrg.organization.model.Organization;

import java.util.Collection;
import java.util.Optional;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Collection<Organization> getAllBySupportOrg_SendEmailBirthday(Boolean tr);

    @Cacheable(cacheNames = "organization")
    @Override
    Optional<Organization> findById(Long aLong);


    @CacheEvict(cacheNames = "organization", allEntries = true)
    @Override
    <S extends Organization> S save(S entity);
}
