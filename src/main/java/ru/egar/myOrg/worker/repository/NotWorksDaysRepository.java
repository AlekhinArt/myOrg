package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;

import java.util.Collection;

public interface NotWorksDaysRepository extends JpaRepository<NotWorksDays, Long> {

//    Collection <NotWorksDays> findAllByWorkHistoryId(Long id);
}
