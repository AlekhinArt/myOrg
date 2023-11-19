package ru.egar.myOrg.worker.repository;

import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.model.notWorksDays.TypeOffDay;

import java.time.LocalDate;
import java.util.Collection;

public interface NotWorksDaysRepository extends JpaRepository<NotWorksDays, Long> {

    @Query(value = "select * from not_work_days " +
            "where (end_date BETWEEN :start and :end) " +
            "or (start_date BETWEEN :start and :end) " +
            "or (start_date <:start and end_date >:end )" +
            "and work_history_id = :whId " +
            "or type_day = :type", nativeQuery = true)
    Collection<NotWorksDays> findAllByWorkHistoryIdAndTypeDayAndStartAfterAndStartBefore(Long whId,
                                                                                         String type,
                                                                                         LocalDate start,
                                                                                         LocalDate end);

//    Collection <NotWorksDays>

    @Query(value = "select * from not_work_days " +
            "where (end_date BETWEEN :start and :end) " +
            "or (start_date BETWEEN :start and :end) " +
            "or (start_date <:start and end_date >:end )" +
            "and work_history_id = :whId", nativeQuery = true)
    Collection<NotWorksDays> findAllByWorkHistoryIdAndStartAfterAndStartBefore(Long whId,
                                                                               LocalDate start,
                                                                               LocalDate end);
}
