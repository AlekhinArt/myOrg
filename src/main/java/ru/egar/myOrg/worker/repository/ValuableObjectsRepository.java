package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egar.myOrg.worker.model.ValuableObject;


import java.util.Collection;


public interface ValuableObjectsRepository extends JpaRepository<ValuableObject, Long> {

    Collection <ValuableObject> findAllByOrganization_Id (Long orgId);

    @Query(value = "select * " +
            "from valuable_object v " +
            "where v.name ilike %:word% and v.org_id = :orgId", nativeQuery = true)
    Collection<ValuableObject> searchValuableObjectByByOrgAndParam(@Param("orgId") Long id, @Param("word") String word);

}
