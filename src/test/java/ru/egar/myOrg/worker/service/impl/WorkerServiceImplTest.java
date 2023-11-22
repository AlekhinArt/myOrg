package ru.egar.myOrg.worker.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egar.myOrg.worker.service.WorkerService;

import static org.junit.jupiter.api.Assertions.*;
@RequiredArgsConstructor
@SpringBootTest
class WorkerServiceImplTest {

private final WorkerService workerService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void create() {
        Assertions.assertEquals(1,2);

    }

    @Test
    void deleteById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void updateById() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void showWorkers() {
    }

    @Test
    void searchWorkers() {
    }

    @Test
    void createWorker() {
    }
}