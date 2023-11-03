package ru.egar.myOrg.worker.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;

import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkerService;


@Slf4j
@Controller
@RequestMapping("/worker")
@Tag(name = "Работник", description = "Взаимодействие с сущностью работник")
@AllArgsConstructor
public class WorkerController {
    // TODO: 28.10.2023  ЗАменить реквест парам на реквест боди
    private final WorkerService workerService;
    private final EmployPositionService empPosService;

    @Operation(summary = "Добавление",
            description = "Добавляем работника")
    @PostMapping
    public String create(@ModelAttribute WorkerCreateDto workerDto, Model model) {
        log.info("Create worker: {}, {}, {}, {}, {}, {}, {}, {}",
                workerDto.getName(),
                workerDto.getSurname(),
                workerDto.getPatronymic(),
                workerDto.getBirthday(),
                workerDto.getPhoneNumber(),
                workerDto.getWorkNow(),
                workerDto.getEmployPosition(),
                workerDto.getStartWork());
        workerService.create(workerDto);
        return "redirect:/";
    }

    @Operation(summary = "Удаление",
            description = "Удаляем работник из бд")
    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Long id) {
        log.info("delete worker {}", id);
        workerService.deleteById(id);
    }

    @Operation(summary = "Все работники")
    @GetMapping
    public String getAll() {
        log.info("Get all workers");
        workerService.getAll();
        return "";
    }

    @GetMapping("/newWorker")
    public String newOrg(Model model) {
        model.addAttribute("employPositions", empPosService.getPositionName());
        return "operations/newWorker";
    }


}
