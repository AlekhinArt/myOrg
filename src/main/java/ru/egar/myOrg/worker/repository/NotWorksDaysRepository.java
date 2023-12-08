package ru.egar.myOrg.worker.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;

import java.time.LocalDate;
import java.util.Collection;

public interface NotWorksDaysRepository extends JpaRepository<NotWorksDays, Long> {

    @Cacheable(cacheNames = "table")
    @Query(value = "select * from not_work_days " +
            "where ((end_date BETWEEN :start and :end) " +
            "or (start_date BETWEEN :start and :end) " +
            "or (start_date <:start and end_date >:end ))" +
            "and work_history_id = :whId " +
            "and type_day = :type", nativeQuery = true)
    Collection<NotWorksDays> findAllByWorkHistoryIdAndTypeDayAndStartAfterAndStartBefore(Long whId,
                                                                                         String type,
                                                                                         LocalDate start,
                                                                                         LocalDate end);
    @Cacheable(cacheNames = "table")
    @Query(value = "select * from not_work_days " +
            "where ((end_date BETWEEN :start and :end) " +
            "or (start_date BETWEEN :start and :end) " +
            "or (start_date <:start and end_date >:end ))" +
            "and work_history_id = :whId "
            , nativeQuery = true)
    Collection<NotWorksDays> findAllByWorkHistoryIdDayAndStartAfterAndStartBefore(Long whId,
                                                                                  LocalDate start,
                                                                                  LocalDate end);
    @CacheEvict(cacheNames = "table", allEntries = true)
    Collection<NotWorksDays> findAllByWorkHistoryId(Long whId);
    @CacheEvict(cacheNames = "table", allEntries = true)
    @Override
    void deleteById(Long id);
    @CacheEvict(cacheNames = "table", allEntries = true)
    <S extends NotWorksDays> S save(S entity);

}
