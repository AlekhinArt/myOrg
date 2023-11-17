package ru.egar.myOrg.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.model.notWorksDays.TypeOffDay;

import java.time.LocalDate;
import java.util.Collection;

public interface NotWorksDaysRepository extends JpaRepository<NotWorksDays, Long> {


    Collection<NotWorksDays> findAllByWorkHistory_IdAndTypeDay(Long whId, TypeOffDay type);

    @Query ("SELECT NotWorksDays from NotWorksDays as n" +
            " where n.workHistory.id =:whId and n.typeDay = :type and n.start > :start and n.end < :end")
    Collection<NotWorksDays> findAllByWorkHistory_IdAndTypeDay(Long whId, TypeOffDay type, LocalDate start, LocalDate end);
//    Collection <NotWorksDays> findAllByWorkHistoryId(Long id);
}
