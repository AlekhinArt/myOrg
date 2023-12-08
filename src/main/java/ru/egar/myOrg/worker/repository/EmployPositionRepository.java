package ru.egar.myOrg.worker.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.worker.model.EmployPosition;

import java.util.List;

public interface EmployPositionRepository extends JpaRepository<EmployPosition, Long> {

    @Query("SELECT position from EmployPosition ")
    List<String> getAllPosition();

    EmployPosition getByPosition(String name);

    @Cacheable(cacheNames = "emsdto")
    @Override
    List<EmployPosition> findAll();

    @CacheEvict(cacheNames = "emsdto", allEntries = true)
    @Override
    <S extends EmployPosition> S save(S entity);

    @CacheEvict(cacheNames = "emsdto", allEntries = true)
    @Override
    void delete(EmployPosition entity);
}
