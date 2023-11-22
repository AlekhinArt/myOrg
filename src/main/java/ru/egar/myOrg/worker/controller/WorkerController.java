package ru.egar.myOrg.worker.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.model.Worker;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkerService;


@Slf4j
@Controller
@RequestMapping("/worker")
@Tag(name = "Работник", description = "Взаимодействие с сущностью работник")
@AllArgsConstructor
public class WorkerController {

    private final WorkerService workerService;
    private final EmployPositionService empPosService;

    @Operation(summary = "Добавление",
            description = "Добавляем работника")
    @PostMapping
    public String create(@ModelAttribute @Valid WorkerCreateDto workerDto) {
        log.info("Create worker: {}, {}, {}, {}, {}, {}, {}, {}, {}",
                workerDto.getName(),
                workerDto.getSurname(),
                workerDto.getPatronymic(),
                workerDto.getBirthday(),
                workerDto.getPhoneNumber(),
                workerDto.getWorkNow(),
                workerDto.getEmployPosition(),
                workerDto.getStartWork(), workerDto.getOrgId());
        workerService.create(workerDto);
        return "redirect:/worker/org/" + workerDto.getOrgId();
    }

    @Operation(summary = "Обновление Данных работника",
            description = "Добавляем работника")
    @PostMapping("/{orgId}/upd")
    public String updWorker(@ModelAttribute @Valid WorkerDto workerDto,
                            @PathVariable Long orgId) {
        log.info("Create worker: {}, {}, {}, {}, {}, {}, {}, {}, {}, {}",
                workerDto.getName(),
                workerDto.getSurname(),
                workerDto.getPatronymic(),
                workerDto.getBirthday(),
                workerDto.getPhoneNumber(),
                workerDto.getWorkNow(),
                workerDto.getGender(),
                workerDto.getFamilyStatus(),
                workerDto.getMinorChildren(),
                workerDto.getOrgId());

        workerService.create(workerDto);
        return "redirect:/worker/org/" + orgId;
    }

    @Operation(summary = "Удаление",
            description = "Удаляем работник из бд")
    @GetMapping("/{orgId}/delete/{id}")
    public String deleteWorker(@PathVariable Long id, @PathVariable Long orgId) {
        log.info("delete worker {}", id);
        workerService.deleteById(id);
        return "redirect:/worker/org/" + orgId;
    }


}
