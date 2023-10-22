package ru.egar.myOrg.organization.repository;

import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.worker.model.Worker;

import java.util.Collection;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Modifying
    @Query("update Organization  o set  o.name=?1, o.inn =?2, o.ogrn = ?3, o.address = ?4, o.phoneNumber = ?5 " +
            "where o.id = ?6")
    Organization updateOrg(String name, Integer inn, Integer ogrn, String address, String phoneNumber, Long id);



}
