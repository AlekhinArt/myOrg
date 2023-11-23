package ru.egar.myOrg.worker.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egar.myOrg.exception.DataConflictException;
import ru.egar.myOrg.exception.ValidException;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.repository.NotWorksDaysRepository;
import ru.egar.myOrg.worker.repository.WorkHistoryRepository;
import ru.egar.myOrg.worker.service.NotWorksDayService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class NotWorkDayServiceImplTest {
    @Autowired
    private NotWorksDayService nwds;
    @Autowired
    private NotWorksDaysRepository nwdr;
    @Autowired
    private WorkHistoryRepository whr;


    private static NotWorksDays nwd1;
    private static NotWorksDays nwd2;
    private static WorkHistory wh;

    @BeforeEach
    void setUp() {
        wh = WorkHistory.builder()
                .id(1L)
                .workNow(true)
                .startWork(LocalDate.parse("2023-10-11"))
                .build();
        nwd1 = NotWorksDays.builder()
                .workHistory(wh)
                .nwdId(1L)
                .start(LocalDate.parse("2022-10-11"))
                .end(LocalDate.parse("2022-10-11"))
                .build();
        nwd2 = NotWorksDays.builder()
                .workHistory(wh)
                .nwdId(2L)
                .start(LocalDate.parse("2022-10-12"))
                .end(LocalDate.parse("2022-10-22"))
                .build();


    }


    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

    @Test
    void updateById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getAllByWhId() {
    }

    @Test
    void getAllByWhIdAndType() {
        LocalDate start = LocalDate.parse("2022-10-10");
        LocalDate end = LocalDate.parse("2022-10-20");
        LocalDate periodStart = LocalDate.parse("2022-10-05");
        LocalDate periodEnd = LocalDate.parse("2022-10-15");
        LocalDate startNon = LocalDate.parse("2022-10-01");
        LocalDate endNon = LocalDate.parse("2022-10-05");


        Duration duration = Duration.between(periodStart, periodEnd);
        Long dperiod = duration.toDays();

//        if (startNon.isAfter())

        Calendar calendar ;


        Long a = start.toEpochDay();
        Long b = end.toEpochDay();
        Long ps = periodStart.toEpochDay();
        Long pe = periodEnd.toEpochDay();
        Long sn = startNon.toEpochDay();
        Long en = endNon.toEpochDay();


        System.out.println("a = " + a);
        System.out.println("b = = " + b);
        System.out.println("ps = " + ps);
        System.out.println("pe = " + pe);
        System.out.println("sn = " + sn);
        System.out.println("en = " + en);


        long sums = a + b;
        long sump = ps + pe;
        long r = b - a;//длина периода в днях


        long k = pe - ps; //длина периода в днях


        System.out.println(r);
        System.out.println(sump);
        System.out.println(sums);
        System.out.println(k);


        if ((a < ps && b < ps) || (a > pe && b > pe)) {

        }


        Period psas = Period.between(startNon, endNon);
        Period period = Period.between(start, end);
        Period period1 = Period.between(periodStart, periodEnd);
        System.out.println(period.equals(period1));
        System.out.println(psas.equals(period1));
        System.out.println(psas);
        System.out.println(period);
        System.out.println(period1);


    }

    @Test
    void saveNotWorksDay() {
        //11-22
        whr.save(wh);
        nwdr.save(nwd1);
        nwdr.save(nwd2);

        NotWorksDays nwd3 = NotWorksDays.builder()
                .workHistory(wh)
                .nwdId(3L)
                .start(LocalDate.parse("2022-10-08"))
                .end(LocalDate.parse("2022-10-12"))
                .build();

        assertThrows(DataConflictException.class, () -> {
            nwds.saveNotWorksDay(nwd3, 1L);
        });

        nwd3.setStart(LocalDate.parse("2022-10-20"));
        nwd3.setEnd(LocalDate.parse("2022-10-19"));
        assertThrows(ValidException.class, () -> {
            nwds.saveNotWorksDay(nwd3, 1L);
        });

        nwd3.setStart(LocalDate.parse("2022-10-20"));
        nwd3.setEnd(LocalDate.parse("2022-10-24"));
        assertThrows(DataConflictException.class, () -> {
            nwds.saveNotWorksDay(nwd3, 1L);
        });

        nwd3.setStart(LocalDate.parse("2022-10-15"));
        nwd3.setEnd(LocalDate.parse("2022-10-15"));
        assertThrows(DataConflictException.class, () -> {
            nwds.saveNotWorksDay(nwd3, 1L);
        });

        nwd3.setStart(LocalDate.parse("2022-10-05"));
        nwd3.setEnd(LocalDate.parse("2022-10-24"));
        assertThrows(DataConflictException.class, () -> {
            nwds.saveNotWorksDay(nwd3, 1L);
        });


        nwd3.setStart(LocalDate.parse("2022-11-24"));
        nwd3.setEnd(LocalDate.parse("2022-11-25"));
        int before = nwdr.findAllByWorkHistoryId(1L).size();
        nwds.saveNotWorksDay(nwd3, 1L);
        int after = nwdr.findAllByWorkHistoryId(1L).size();

        assertNotEquals(before, after);

    }

    @Test
    void notWorkDayByTypeAndDate() {


    }

    @Test
    void getSumNotWorkDays() {
        List<NotWorksDays> notWorksDaysList = new ArrayList<>();
        notWorksDaysList.add(nwd1);
        notWorksDaysList.add(nwd2);
        Long sum = nwds.getSumNotWorkDays(notWorksDaysList);

        assertEquals(3, sum);
    }
}