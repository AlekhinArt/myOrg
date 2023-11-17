package ru.egar.myOrg.worker.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.exception.ValidException;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.model.notWorksDays.TypeOffDay;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.NotWorksDayService;
import ru.egar.myOrg.worker.service.impl.WorkerHistoryService;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/wh")
@Tag(name = "История работы", description = "Взаимодействие с сущностью NotWorksDAys")
@AllArgsConstructor
public class WorkHistoryController {

    private final NotWorksDayService nwds;
    private final WorkerHistoryService whs;
    private final EmployPositionService emps;


    @GetMapping("/{orgId}/{whId}")
    public String getAllNotWorkDayByWh(@PathVariable Long whId, @PathVariable Long orgId, Model model) {
        log.info("getAllNotWorkDayByWh historyId {} ", whId);
        WorkHistory workHistory = whs.getById(whId)
                .orElseThrow(() -> new NotFoundException("История не найдена"));
        model.addAttribute("ndws", workHistory.getNotWorksDays());
        model.addAttribute("whId", whId);
        model.addAttribute("workerId",workHistory.getWorker().getId());
        model.addAttribute("orgId", orgId);
        model.addAttribute("sum", whs.getAllNotWorkDays(workHistory.getNotWorksDays()));

        return "notWorksDays/notWorksDays";
    }

    @GetMapping("/{orgId}/{whId}/bt")
    public String getNotWorkDaysByType(@PathVariable Long orgId, @PathVariable Long whId,
                                       @ModelAttribute TypeOffDay typeOffDay, Model model) {
        log.info("getNotWorkDaysByType historyId {}, type {}", whId, typeOffDay);
        WorkHistory workHistory = whs.getById(whId)
                .orElseThrow(() -> new NotFoundException("История не найдена"));
        model.addAttribute("ndws", whs.notWorkDayByType(typeOffDay, whId));
        model.addAttribute("whId", whId);
        model.addAttribute("workerId", workHistory.getWorker().getId());
        model.addAttribute("orgId", orgId);
        model.addAttribute("sum", whs.getAllNotWorkDays(whs.notWorkDayByType(typeOffDay, whId)));

        return "notWorksDays/notWorksDays";
    }


    @GetMapping("/{orgId}/create/{id}")
    public String createNotWorkDaysMVC(@PathVariable Long id, @PathVariable Long orgId, Model model) {
        log.info("createNotWorkDaysMVC historyId {} ", id);
        model.addAttribute("whs", whs.getById(id)
                .orElseThrow(() -> new NotFoundException("История не найдена")));
        model.addAttribute("maxTime", LocalDate.now());
        model.addAttribute("orgId", orgId);


        return "notWorksDays/newNWD";
    }


    @PostMapping("{orgId}/{whId}")
    public String saveNotWorkDay(@ModelAttribute NotWorksDays nwd, @PathVariable Long whId,
                                 @PathVariable Long orgId, Model model) {
        log.info("Добавили не рабочий день {}, {}, {}, {},{}", nwd.getNwdId(), nwd.getStart(), nwd.getEnd(), nwd.getTypeDay(), nwd.getNote());
        whs.saveNotWorksDay(nwd, whId);


        return "redirect:/wh/" + orgId + "/" + whId;
    }

    @GetMapping("/{orgId}/{whId}/delete/{id}")
    public String deleteNotWorkDay(@PathVariable Long id,
                                   @PathVariable Long whId,
                                   @PathVariable Long orgId) {
        log.info("delete not work day id {}", id);
        nwds.deleteById(id);
        return "redirect:/" + orgId + "/" + whId;

    }

    @GetMapping("/{orgId}/{workerId}/edit/{whId}")
    public String layOffWorkerMVC(@PathVariable Long whId,
                                  @PathVariable Long workerId,
                                  @PathVariable Long orgId,
                                  Model model) {
        model.addAttribute("wh", whs.getById(whId)
                .orElseThrow(() -> new NotFoundException("История не найдена")));
        model.addAttribute("workerId", workerId);
        model.addAttribute("orgId", orgId);


        return "workHistory/layOffWorker";
    }


    @PostMapping("/{orgId}/{workerId}/layOf/{whId}")
    public String layOffWorker(@ModelAttribute WorkHistory workHistory,
                               @PathVariable Long whId,
                               @PathVariable Long workerId,
                               @PathVariable Long orgId) {

        whs.layOffWorker(workHistory, whId);
        return "redirect:/worker/" + workerId + "/get/" + whId;
    }

    @GetMapping("/{orgId}/{workerId}/create/")
    public String createWorkHistoryMVC(@PathVariable Long workerId,
                                       @PathVariable Long orgId,
                                       Model model) {
        model.addAttribute("employPositions", emps.getAll());
        model.addAttribute("workerId", workerId);
        model.addAttribute("orgId", orgId);

        return "workHistory/newWorkHistory";
    }

}
