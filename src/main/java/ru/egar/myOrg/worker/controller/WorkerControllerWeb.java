package ru.egar.myOrg.worker.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkerService;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/worker")
@AllArgsConstructor
public class WorkerControllerWeb {
    private final WorkerService workerService;
    private final EmployPositionService empPosService;

    @GetMapping("/newWorker/{id}")
    public String newWorker(@PathVariable Long id, Model model) {
        model.addAttribute("employPositions", empPosService.getPositionName());
        model.addAttribute("orgId", id);
        model.addAttribute("currentDate", LocalDate.now());
        return "workers/newWorker";
    }

    // TODO: 07.11.2023 поставить отлов ошибки на более нижний уровень
    @GetMapping("/org/{orgId}")
    public String workersByOrg(@PathVariable Long orgId, Model model) {
        log.info("Get workers by ORG orgID{}", orgId);
        try {
            model.addAttribute("workers", workerService.showWorkers(orgId));

        } catch (Exception e) {
            model.addAttribute("employPositions", empPosService.getPositionName());
            model.addAttribute("currentDate", LocalDate.now());
            return "workers/newWorker";
        } finally {
            model.addAttribute("orgId", orgId);

        }
        return "workers/workMain";
    }

    @GetMapping("/org/{orgId}/search")
    public String searchByOrgAndParam(@PathVariable Long orgId, Model model,
                                      @RequestParam String word,
                                      @RequestParam String pos) {
        log.info("Search workers by ORG orgID{}, search {}, pos {}", orgId, word, pos);
        model.addAttribute("workers", workerService.searchWorkers(orgId, word, pos));
        model.addAttribute("employPositions", empPosService.getPositionName());
        model.addAttribute("orgId", orgId);

        return "workers/workMain";
    }


    @Operation(summary = "Получить работника",
            description = "Получаем всю информацию о работнике")
    @GetMapping("/{orgId}/get/{id}")
    public String getWorker(@PathVariable Long id, @PathVariable Long orgId, Model model) {
        log.info("get worker {}", id);
        model.addAttribute("worker", workerService.getById(id)
                .orElseThrow(() -> new NotFoundException("Работник не найден")));
        model.addAttribute("orgId", orgId);
        return "workers/fullWorker";
    }


}
