package ru.egar.myOrg.worker.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ru.egar.myOrg.worker.service.NotWorksDayService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest

public class NotWorkDayServiceImplTest {
    @Autowired
    private NotWorksDayService nwds;


    private static NotWorksDays nwd1;
    private static NotWorksDays nwd2;


    @BeforeEach
    void setUp() {
        nwd1 = NotWorksDays.builder()
                .nwdId(1L)
                .start(LocalDate.parse("2022-10-10"))
                .end(LocalDate.parse("2022-10-20"))
                .build();
        nwd2 = NotWorksDays.builder()
                .nwdId(2L)
                .start(LocalDate.parse("2022-10-11"))
                .end(LocalDate.parse("2022-10-12"))
                .build();
    }

//    @AfterEach
//    void tearDown() {
//    }

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
    }

    @Test
    void saveNotWorksDay() {
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

        assertEquals(13, sum);
        nwd1.setStart(LocalDate.parse("2022-10-20"));
        nwd1.setEnd(LocalDate.parse("2022-10-20"));
        notWorksDaysList.add(nwd1);
        sum = nwds.getSumNotWorkDays(notWorksDaysList);

        assertEquals(14, sum);


    }
}