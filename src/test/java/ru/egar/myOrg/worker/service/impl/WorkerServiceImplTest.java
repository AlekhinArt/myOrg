package ru.egar.myOrg.worker.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egar.myOrg.document.model.PaperDocument;
import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.organization.mapper.OrganizationMapper;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.organization.model.SupportOrg;
import ru.egar.myOrg.organization.service.OrganizationService;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.model.EmployPosition;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.model.enumerated.FamilyStatus;
import ru.egar.myOrg.worker.model.enumerated.Gender;
import ru.egar.myOrg.worker.repository.EmployPositionRepository;
import ru.egar.myOrg.worker.repository.WorkerRepository;
import ru.egar.myOrg.worker.service.WorkerService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class WorkerServiceImplTest {
    @Autowired
    private OrganizationMapper orgMap;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private EmployPositionRepository employPositionRepository;

    WorkerDto workerDto;
    Worker worker;
    Organization organization;
    Organization organization2;
    PaperDocument paperDocument;
    WorkHistory workHistory;
    WorkerCreateDto workerCreateDto;
    EmployPosition employPosition;
    SupportOrg supportOrg;


    @BeforeAll
    public static void start() {

    }

    @BeforeEach
    void setUp() {
        supportOrg = SupportOrg.builder()
                .sendEmailBirthday(true)
                .build();
        paperDocument = PaperDocument.builder()
                .number("1234")
                .series("123224")
                .issued(LocalDate.parse("2020-10-11"))
                .build();
        employPosition = EmployPosition.builder()
                .position("sadasd")
                .jobDescription("asdasd")
                .codType("004")
                .build();
        organization = Organization.builder()
//                .id(1L)
                .address("������")
                .name("���")
                .inn("1234567891")
                .ogrn("1234567891234")
                .phoneNumber("89998887766")
                .zip("111000")
                .supportOrg(supportOrg)
                .build();
        organization2 = Organization.builder()
//                .id(2L)
                .address("�����")
                .name("���")
                .inn("111111111")
                .ogrn("22222222")
                .phoneNumber("89998887766")
                .zip("111000")
                .supportOrg(supportOrg)
                .build();
        workerDto = WorkerDto.builder()
//                .organization(organization)
                .orgId(3L)
                .workNow(true)
                .name("Вася")
                .gender(Gender.MALE)
                .surname("Пупкин")
                .patronymic("Щавельев")
                .familyStatus(FamilyStatus.MARRIED)
                .birthday(LocalDate.parse("2000-10-10", DateTimeFormatter.ISO_LOCAL_DATE))
                .minorChildren(false)
                .phoneNumber("+79603184060")
                .build();
        worker = Worker.builder()
                .delete(false)
                .organization(organization)
                .workNow(true)
                .name("Не Вася")
                .gender(Gender.MALE)
                .surname("Не Пупкин")
                .patronymic("Не Щавельев")
                .familyStatus(FamilyStatus.SINGLE)
                .birthday(LocalDate.parse("2000-01-01"))
                .minorChildren(false)
                .phoneNumber("+79603184060")
                .build();
        workerCreateDto = WorkerCreateDto.builder()
                .orgId(3L)
                .workNow(true)
                .name("Вася")
                .gender(Gender.MALE)
                .surname("Пупкин")
                .patronymic("Щавельев")
                .familyStatus(FamilyStatus.MARRIED)
                .birthday(LocalDate.parse("2000-10-10", DateTimeFormatter.ISO_LOCAL_DATE))
                .minorChildren(false)
                .phoneNumber("+79603184060")
                .employPosition("123")
                .startWork(LocalDate.parse("2022-12-01"))
                .build();

    }

    @AfterEach
    void tearDown() {
    }

    @Order(1)
    @Test
//    @Sql({"/clear.sql"})
    void letsGo() {

        employPositionRepository.save(employPosition);


    }


    @Order(2)
    @Test
    void create() {
        OrganizationDto organizationDto = organizationService.create(orgMap.toOrganizationDto(organization));
        OrganizationDto organizationDto2 = organizationService.create(orgMap.toOrganizationDto(organization2));


        workerDto.setOrgId(organizationDto.getId());
        WorkerDto workerDtoAfter = workerService.create(workerDto);
        workerDto.setId(1L);

        assertEquals(workerDto.getId(), workerDtoAfter.getId());
        assertEquals(workerDto.getName(), workerDtoAfter.getName());
        assertEquals(workerDto.getOrgId(), workerDtoAfter.getOrganization().getId());
        assertEquals(workerDto.getBirthday(), workerDtoAfter.getBirthday());


    }

    @Order(3)
    @Test
    void deleteById() {
        workerService.deleteById(1L);
        Worker afterWorker = workerRepository.findById(1L).orElseThrow();
        assertEquals(afterWorker.getDelete(), true);
    }

    @Order(4)
    @Test
    void getAll() {
        int after = workerService.getAll().size();
        assertEquals(1, after);
    }

    @Order(5)
    @Test
    void getById() {
        WorkerDto workerDto1 = workerService.getById(1L).orElseThrow();
        assertEquals(workerDto.getPatronymic(), workerDto1.getPatronymic());
        assertEquals(workerDto.getName(), workerDto1.getName());
        assertEquals(workerDto.getOrgId(), workerDto1.getOrganization().getId());
        assertEquals(workerDto.getBirthday(), workerDto1.getBirthday());
    }

    @Order(6)
    @Test
    void updateById() {
    }

    @Order(7)
    @Test
    void testCreate() {
        WorkerDto workerDto1 = workerService.create(workerCreateDto, paperDocument);

        assertEquals(workerDto.getSurname(), workerDto1.getSurname());
        assertEquals(workerDto.getBirthday(), workerDto1.getBirthday());
        assertEquals(workerDto.getPatronymic(), workerDto1.getPatronymic());
        assertEquals(workerDto.getOrgId(), workerDto1.getOrganization().getId());
    }

    @Order(8)
    @Test
    void showWorkers() {
        int a = workerService.showWorkers(3L).size();

        assertEquals(2, a);


    }

    @Order(9)
    @Test
    void searchWorkers() {
        WorkerDto newWorkerDto = workerDto;
        newWorkerDto.setName("Маша");
        newWorkerDto.setOrgId(2L);
        newWorkerDto.setOrganization(organization2);
        workerService.create(newWorkerDto);
        int a = workerService.searchWorkers(2L, "Маша", "true").size();
        assertEquals(1, a);
        a = workerService.searchWorkers(2L, "Маша", "false").size();
        assertEquals(0, a);
        a = workerService.searchWorkers(2L, "", "true").size();
        assertEquals(1, a);


    }


    @Order(11)
    @Test
    void findAllByOrganizationInAndBirthday() {
        //сложности с проверкой метода
        // h2 не понимает синтаксиса пострге
        Worker w1 = workerRepository.findById(1L).orElseThrow();
        w1.setBirthday(LocalDate.now());
        workerRepository.save(w1);

        List<OrganizationDto> orgs = organizationService.getAll();
        List<Long> orgsId = new ArrayList<>();
        for (OrganizationDto org : orgs) {
            orgsId.add(org.getId());
        }
        System.out.println(orgs.size());
//        Collection<WorkerShowDto> forSendMail = workerService.getForSendMail(orgsId);
//        System.out.println(forSendMail.size());
//        assertEquals(1, forSendMail.size());
    }


}