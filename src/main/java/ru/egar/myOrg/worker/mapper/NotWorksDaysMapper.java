package ru.egar.myOrg.worker.mapper;

import lombok.extern.slf4j.Slf4j;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDaysWithDaysList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class NotWorksDaysMapper {


    public static NotWorksDaysWithDaysList toList(NotWorksDays notWorksDays) {
        return NotWorksDaysWithDaysList.builder()
                .typeDay(notWorksDays.getTypeDay())
                .end(notWorksDays.getEnd())
                .start(notWorksDays.getStart())
                .note(notWorksDays.getNote())
                .notWorksDays(getlist(notWorksDays.getStart(), notWorksDays.getEnd()))
                .build();


    }

    private static List<LocalDate> getlist(LocalDate start, LocalDate end) {
        List<LocalDate> list = new ArrayList<>();
        while (start.isBefore(end)) {
            log.info("{}",start);
            list.add(start);
            start = start.plusDays(1L);
        }
        list.add(end);
        return list;

    }
}
