package ru.egar.myOrg.worker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.document.model.PaperDocument;
import ru.egar.myOrg.document.service.DocumentService;
import ru.egar.myOrg.document.service.TypeDocumentService;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.dto.WorkerCreateDto;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkHistoryService;
import ru.egar.myOrg.worker.service.WorkerService;

import java.time.LocalDate;
import java.time.Month;

@Slf4j
@Controller
@RequestMapping("/worker")
@Tag(name = "Работник", description = "Взаимодействие с сущностью работник")
@AllArgsConstructor
public class WorkerController {
    private final WorkerService workerService;
    private final EmployPositionService empPosService;
    private final WorkHistoryService workHistoryService;
    private final DocumentService documentService;
    private final TypeDocumentService typeDocumentService;

    @Operation(summary = "Добавление",
            description = "Добавляем работника")
    @PostMapping
    public String create(@ModelAttribute @Valid WorkerCreateDto workerDto,
                         @ModelAttribute @Valid PaperDocument paperDocument) {
        log.info("Create worker: {}, {}, {}, {}, {}, {}, {}, {}, {}",
                workerDto.getName(),
                workerDto.getSurname(),
                workerDto.getPatronymic(),
                workerDto.getBirthday(),
                workerDto.getPhoneNumber(),
                workerDto.getWorkNow(),
                workerDto.getEmployPosition(),
                workerDto.getStartWork(), workerDto.getOrgId());
        log.info("Create passport: {}, {}, {}, {}, {}",
                paperDocument.getSeries(),
                paperDocument.getNumber(),
                paperDocument.getIssued(),
                paperDocument.getWhoIssued(),
                paperDocument.getTypeDocument());
        workerService.create(workerDto, paperDocument);
        return "redirect:/worker/org/" + workerDto.getOrgId();
    }

    @Operation(summary = "Обновление Данных работника",
            description = "Обновление данных ")
    @PutMapping("/upd/{orgId}")
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
    @DeleteMapping("/{workerId}/{orgId}/delete")
    public String deleteWorker(@PathVariable Long workerId, @PathVariable Long orgId) {
        log.info("delete worker {}", workerId);
        workerService.deleteById(workerId);
        return "redirect:/worker/org/" + orgId;
    }

    @GetMapping("/newWorker/{orgId}")
    public String newWorker(@PathVariable Long orgId, Model model) {
        model.addAttribute("employPositions", empPosService.getPositionName());
        model.addAttribute("orgId", orgId);
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("docktype", typeDocumentService.getAllByIdentity(true));
        return "workers/newWorker";
    }

    @GetMapping("/{workerId}/edit/{orgId}")
    public String updWorker(@PathVariable Long workerId, @PathVariable Long orgId, Model model) {
        model.addAttribute("employPositions", empPosService.getPositionName());
        model.addAttribute("orgId", orgId);
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("workerId", workerId);
        model.addAttribute("worker", workerService.getById(workerId)
                .orElseThrow(() -> new NotFoundException("Работник не найден")));

        return "workers/updateWorker";

    }


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
    @GetMapping("/{workerId}/get/{orgId}")
    public String getWorker(@PathVariable Long workerId, @PathVariable Long orgId, Model model) {
        log.info("get worker {}", workerId);
        model.addAttribute("worker", workerService.getById(workerId)
                .orElseThrow(() -> new NotFoundException("Работник не найден")));
        model.addAttribute("whs", workHistoryService.getByWorkerId(workerId));
        model.addAttribute("orgId", orgId);
        model.addAttribute("paperDocument", documentService.findByWorkerIdAndActualTrue(workerId));
        model.addAttribute("Months", Month.values());

        return "workers/fullWorker";
    }


}
