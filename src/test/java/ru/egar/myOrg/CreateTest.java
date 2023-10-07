package ru.egar.myOrg;

import org.junit.jupiter.api.BeforeAll;
import ru.egar.myOrg.document.Graduate;
import ru.egar.myOrg.document.PaperDocument;
import ru.egar.myOrg.document.Passport;
import ru.egar.myOrg.organization.Organization;
import ru.egar.myOrg.worker.EmployPosition;
import ru.egar.myOrg.worker.WorkHistory;
import ru.egar.myOrg.worker.Worker;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateTest {

    Worker worker;
    EmployPosition employPosition;
    Organization organization;
    Graduate graduate;
    Passport passport;
    List<PaperDocument> paperDocuments;
    List<WorkHistory> workHistories;


    @BeforeAll
    public void start() {
        paperDocuments = new ArrayList<>();
        passport = Passport.builder()
                .id(1)
                .number(1233)
                .series(2333)
                .issued(LocalDate.parse("2020-09-01"))
                .whoIssued("Странный отдел странных дел")
                .build();
        graduate = Graduate.builder()
                .id(1)
                .institution("Институт странных дел")
                .grade("noob")
                .specialization("Doctor")
                .startDate(LocalDate.parse("2010-09-01"))
                .lastDate(LocalDate.parse("2015-09-01"))
                .build();
        paperDocuments.add(passport);
        paperDocuments.add(graduate);

        workHistories = new ArrayList<>();
        List<LocalDate> daysOf = new ArrayList<>();
        List<LocalDate> sickDays = new ArrayList<>();
        List<LocalDate> vacation = new ArrayList<>();
//daysOf.add
        WorkHistory workHistory = WorkHistory.builder()
                .id(1)
                .workNow(true)
                .employPosition(new EmployPosition(1, "Redactor", "new redactor"))
                .startWork(LocalDate.parse("2022-01-03"))

                .build();


        worker = Worker.builder()
                .id(1)
                .workNow(true)
                .name("Alex")
                .surname("Ivanov")
                .patronymic("Ivanivoch")
                .birthday(LocalDate.parse("1990-09-01"))
                .phoneNumber("+79603184060")
//                .documents(paperDocuments)
                .workHistory(workHistories)
                .build();


    }


}
