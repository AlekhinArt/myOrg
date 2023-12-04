package ru.egar.myOrg.organization.service;

import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.organization.mapper.OrganizationMapper;
import ru.egar.myOrg.organization.model.Organization;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OrganizationServiceImplTest {
    @Autowired
    private OrganizationMapper orgMap;
    @Autowired
    private OrganizationService orgSer;
    Organization organization;
    OrganizationDto organizationDto;
    OrganizationDto organizationDto2;

    @BeforeEach
    void setUp() {
        organization = Organization.builder()
                .address("Москва")
                .name("Имя")
                .inn("1234567891")
                .ogrn("1234567891234")
                .phoneNumber("89998887766")
                .zip("111000")
                .build();
        organizationDto = OrganizationDto.builder()
                .address("Москва")
                .name("Имя")
                .inn("1234567891")
                .ogrn("1234567891234")
                .phoneNumber("89998887766")
                .zip("111000")
                .build();
        organizationDto2 = OrganizationDto.builder()
                .address("Питер")
                .name("Ями")
                .inn("1234567123")
                .ogrn("1231237891234")
                .phoneNumber("89998887766")
                .zip("111000")
                .build();

    }


    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    @DisplayName("Check mapper ")
    void mapperTest() {
        OrganizationDto organizationDtoAfter = orgMap.toOrganizationDto(organization);
        Organization organizationAfter = orgMap.toOrganization(organizationDto);

        assertEquals(organization.getName(), organizationAfter.getName());
        assertEquals(organizationDto.getName(), organizationDtoAfter.getName());
        assertReflectionEquals(organizationDto, organizationDtoAfter);
        assertReflectionEquals(organization, organizationAfter);

    }

    @Test
    @Order(3)
    @DisplayName("Get all organization")
    void getAll() {
        List<OrganizationDto> list = orgSer.getAll();

        assertEquals(2, list.size());
    }

    @Order(4)
    @Test
    @DisplayName("Check get from bd")
    void getById() {
        OrganizationDto fromDb = orgSer.getById(1L).orElseThrow();
        organizationDto.setId(1L);
        assertReflectionEquals(organizationDto, fromDb);


    }

    @Test
    @Order(2)
    @DisplayName("Check create")
    void create() {
        OrganizationDto organizationDtoAfter = orgSer.create(organizationDto);
        OrganizationDto organizationDtoAfter2 = orgSer.create(organizationDto2);

        assertEquals(1L, organizationDtoAfter.getId());
        organizationDto.setId(1L);
        assertEquals(2L, organizationDtoAfter2.getId());
        organizationDto2.setId(2L);


        assertReflectionEquals(organizationDto, organizationDtoAfter);
        assertReflectionEquals(organizationDto2, organizationDtoAfter2);


    }

    @Test
    @Order(5)
    void updateById() {
        organizationDto.setName("Test is good");
        OrganizationDto organizationDtoAfter = orgSer.updateById(1L, organizationDto);

        assertReflectionEquals(organizationDto, organizationDtoAfter);
    }

    @Order(6)
    @Test
    void deleteById() {

        int before = orgSer.getAll().size();
        orgSer.deleteById(1L);
        int after = orgSer.getAll().size();
        assertNotEquals(before, after);

    }

}