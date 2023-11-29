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
import ru.egar.myOrg.document.service.DocumentService;
import ru.egar.myOrg.document.service.TypeDocumentService;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkHistoryService;
import ru.egar.myOrg.worker.service.WorkerService;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@Slf4j
@Controller
@RequestMapping("/worker")
@AllArgsConstructor
public class WorkerControllerWeb {
    private final WorkerService workerService;
    private final EmployPositionService empPosService;
    private final WorkHistoryService whs;
    private final DocumentService ds;
    private final TypeDocumentService tds;

    @GetMapping("/newWorker/{id}")
    public String newWorker(@PathVariable Long id, Model model) {
        model.addAttribute("employPositions", empPosService.getPositionName());
        model.addAttribute("orgId", id);
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("docktype", tds.getAllByIdentity(true));
        return "workers/newWorker";
    }

    @GetMapping("/{orgId}/edit/{id}")
    public String updWorker(@PathVariable Long id, @PathVariable Long orgId, Model model) {
        model.addAttribute("employPositions", empPosService.getPositionName());
        model.addAttribute("orgId", orgId);
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("workerId", id);
        model.addAttribute("worker", workerService.getById(id)
                .orElseThrow(() -> new NotFoundException("Работник не найден")));

        return "workers/updateWorker";

    }


    // TODO: 07.11.2023 поставить отлов ошибки на более нижний уровень
    @GetMapping("/org/{orgId}")
    public String workersByOrg(@PathVariable Long orgId, Model model) {
        log.info("Get workers by ORG orgID {}", orgId);
        try {
            model.addAttribute("workers", workerService.showWorkers(orgId));
        } catch (Exception e) {
            model.addAttribute("currentDate", LocalDate.now());
            log.info("Что-то пошло не так {} ", e.getMessage());
            return "workers/newWorker";
        } finally {
            model.addAttribute("orgId", orgId);
            model.addAttribute("employPositions", empPosService.getPositionName());

        }
        return "workers/workMain";
    }

    @GetMapping("/org/{orgId}/search")
    public String searchByOrgAndParam(@PathVariable Long orgId, Model model,
                                      @RequestParam String word,
                                      @RequestParam(defaultValue = "true") String workNow) {
        log.info("Search workers by ORG orgID{}, search {}, workNow {}", orgId, word, workNow);
        model.addAttribute("workers", workerService.searchWorkers(orgId, word, workNow));
        model.addAttribute("employPositions", empPosService.getPositionName());
        model.addAttribute("orgId", orgId);
        return "workers/workMain";
    }

    @Operation(summary = "Получить работника",
            description = "Получаем всю информацию о работнике")
    @GetMapping("/{orgId}/get/{workerId}")
    public String getWorker(@PathVariable Long workerId, @PathVariable Long orgId, Model model) {
        log.info("get worker {}", workerId);
        model.addAttribute("worker", workerService.getById(workerId)
                .orElseThrow(() -> new NotFoundException("Работник не найден")));
        model.addAttribute("whs", whs.getByWorkerId(workerId));
        model.addAttribute("orgId", orgId);
        model.addAttribute("paperDocument", ds.findByWorkerIdAndActualTrue(workerId));
        model.addAttribute("Months", Month.values());

        return "workers/fullWorker";
    }


}
