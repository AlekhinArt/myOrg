package ru.egar.myOrg.worker.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.service.WorkerService;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/worker")
@Tag(name = "Работник", description = "Взаимодействие с сущностью работник")
@AllArgsConstructor
public class WorkerController {
    // TODO: 28.10.2023  ЗАменить реквест парам на реквест боди
    private final WorkerService workerService;

    @Operation(summary = "Добавление",
            description = "Добавляем работника")
    @PostMapping
    public WorkerDto create(@RequestParam Boolean workNow,
                            @RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam String patronymic,
                            @RequestParam LocalDate birthday,
                            @RequestParam String phoneNumber) {
        log.info("Create worker: {}, {}, {}, {}, {}, {}", workNow, name, surname, patronymic, birthday, phoneNumber);
        return workerService.create(WorkerDto.builder()
                .workNow(workNow)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .birthday(birthday)
                .phoneNumber(phoneNumber)
                .build());
    }
    @Operation(summary = "Удаление",
            description = "Удаляем работник из бд")
    @DeleteMapping("/{id}")
    public void deleteWorker (@PathVariable Long id){
        log.info("delete worker {}", id);
        workerService.deleteById(id);
    }
    @Operation (summary = "Все работники")
    @GetMapping
    public String getAll(){
        log.info("Get all workers");
        workerService.getAll();
        return "";
    }









}
