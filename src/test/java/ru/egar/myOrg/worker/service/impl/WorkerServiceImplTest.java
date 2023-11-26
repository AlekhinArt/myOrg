package ru.egar.myOrg.worker.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egar.myOrg.document.model.PaperDocument;
import ru.egar.myOrg.document.repository.PassportRepository;
import ru.egar.myOrg.organization.mapper.OrganizationMapper;
import ru.egar.myOrg.organization.model.Organization;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class WorkerServiceImplTest {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private PassportRepository passportRepository;
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

    @BeforeAll
    public static void start() {

    }

    @BeforeEach
    void setUp() {
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
                .id(1L)
                .address("Москва")
                .name("Имя")
                .inn("1234567891")
                .ogrn("1234567891234")
                .phoneNumber("89998887766")
                .zip("111000")
                .build();
        organization2 = Organization.builder()
                .id(2L)
                .address("Питер")
                .name("Имя")
                .inn("111111111")
                .ogrn("22222222")
                .phoneNumber("89998887766")
                .zip("111000")
                .build();
        workerDto = WorkerDto.builder()
                .organization(organization)
                .orgId(1L)
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
                .orgId(1L)
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
    void letsGo() {
        //инициализируем тесты
        organizationService.create(OrganizationMapper.toOrganizationDto(organization));
        organizationService.create(OrganizationMapper.toOrganizationDto(organization2));
        employPositionRepository.save(employPosition);


    }


    @Order(2)
    @Test
    void create() {
        WorkerDto workerDtoAfter = workerService.create(workerDto);
        workerDto.setId(1L);
        assertEquals(workerDto.getId(), workerDtoAfter.getId());
        assertEquals(workerDto.getName(), workerDtoAfter.getName());
        assertEquals(workerDto.getOrganization().getId(), workerDtoAfter.getOrganization().getId());
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
        assertEquals(workerDto.getOrganization().getId(), workerDto1.getOrganization().getId());
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
        int a = workerService.showWorkers(1L).size();

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

    @Order(10)
    @Test
    void createWorker() {
        Worker newWorker = workerService.createWorker(worker);
        assertEquals(worker.getName(), newWorker.getName());
        assertEquals(worker.getPhoneNumber(), newWorker.getPhoneNumber());
        assertEquals(worker.getGender(), newWorker.getGender());
        assertEquals(worker.getDelete(), newWorker.getDelete());
    }
}