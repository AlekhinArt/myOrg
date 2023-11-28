package ru.egar.myOrg.worker.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Strings;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.NotWorksDayService;
import ru.egar.myOrg.worker.service.WorkerService;
import ru.egar.myOrg.worker.service.impl.WorkerHistoryService;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Collection;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/wh")
@Tag(name = "История работы", description = "Взаимодействие с сущностью NotWorksDAys")
@AllArgsConstructor
public class WorkHistoryController {

    private final NotWorksDayService nwds;
    private final WorkerHistoryService whs;
    private final EmployPositionService emps;
    private final WorkerService ws;


    @GetMapping("/{orgId}/{whId}")
    public String getAllNotWorkDayByWh(@PathVariable Long whId, @PathVariable Long orgId, Model model) {
        log.info("getAllNotWorkDayByWh historyId {} ", whId);
        WorkHistory workHistory = whs.getById(whId)
                .orElseThrow(() -> new NotFoundException("История не найдена"));
        Collection<NotWorksDays> notWorksDays = nwds.getAllByWhId(whId);
        model.addAttribute("ndws", notWorksDays);
        model.addAttribute("whId", whId);
        model.addAttribute("workerId", workHistory.getWorker().getId());
        model.addAttribute("orgId", orgId);
        model.addAttribute("sum", nwds.getSumNotWorkDays(notWorksDays));
        model.addAttribute("dateNow", LocalDate.now());


        return "notWorksDays/notWorksDays";
    }

    @GetMapping("/{orgId}/{whId}/bt")
    public String getNotWorkDaysByType(@PathVariable Long orgId, @PathVariable Long whId,
                                       @RequestParam String typeOffDay, Model model,
                                       @RequestParam String start, @RequestParam String end) {
        log.info("getNotWorkDaysByType historyId {}, type {}, start {} , end {}", whId, typeOffDay, start, end);
        WorkHistory workHistory = whs.getById(whId)
                .orElseThrow(() -> new NotFoundException("История не найдена"));
        model.addAttribute("ndws", nwds.notWorkDayByTypeAndDate(typeOffDay, whId, start, end));
        model.addAttribute("whId", whId);
        model.addAttribute("workerId", workHistory.getWorker().getId());
        model.addAttribute("orgId", orgId);
        model.addAttribute("sum", nwds.getSumNotWorkDays(nwds.notWorkDayByTypeAndDate(typeOffDay, whId, start, end)));
        model.addAttribute("dateNow", LocalDate.now());

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
        nwds.saveNotWorksDay(nwd, whId);


        return "redirect:/wh/" + orgId + "/" + whId;
    }

    @GetMapping("/{orgId}/{whId}/delete/{id}")
    public String deleteNotWorkDay(@PathVariable Long id,
                                   @PathVariable Long whId,
                                   @PathVariable Long orgId) {
        log.info("delete not work day id {}", id);
        nwds.deleteById(id);
        return "redirect:/wh/" + orgId + "/" + whId;

    }

    @GetMapping("/{orgId}/{workerId}/delete/wh/{whId}")
    public String deleteWorkHistory(@PathVariable Long whId,
                                    @PathVariable Long workerId,
                                    @PathVariable Long orgId) {
        log.info("delete workHistory {}", whId);
        whs.deleteById(whId);
        whs.changeWorkerStatus(workerId);
        return "redirect:/worker/" + orgId + "/get/" + workerId;

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
        return "redirect:/worker/" + orgId + "/get/" + workerId;
    }

    @GetMapping("/{orgId}/{workerId}/create/")
    public String createWorkHistoryMVC(@PathVariable Long workerId,
                                       @PathVariable Long orgId,
                                       Model model) {
        model.addAttribute("employPositions", emps.getAll());
        model.addAttribute("workerId", workerId);
        model.addAttribute("orgId", orgId);
        model.addAttribute("currentDate", LocalDate.now());

        return "workHistory/newWorkHistory";
    }

    @PostMapping("/{orgId}/{workerId}/create")
    public String createWorkHistory(@PathVariable Long workerId,
                                    @PathVariable Long orgId,
                                    @RequestParam LocalDate startWork,
                                    @RequestParam Long emplPosId,
                                    Model model) {
        log.info("createWorkHistory workerId {}, orgId {}, wh.start {}, emplPosId {}",
                workerId, orgId, startWork, emplPosId);
        whs.createNewWorkHistory(workerId, startWork, emplPosId);


        return "redirect:/worker/" + orgId + "/get/" + workerId;
    }

    @GetMapping("/{orgId}/{workerId}/{whId}/calendar")
    public String workHours(@PathVariable Long workerId,
                            @PathVariable Long orgId,
                            @PathVariable Long whId,
//                            @RequestParam String start,
//                            @RequestParam String end,
                            Model model) {


        model.addAttribute("workerId", workerId);
        model.addAttribute("orgId", orgId);
        model.addAttribute("month", LocalDate.parse("2023-11-01").getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        model.addAttribute("worker", ws.getById(orgId).orElseThrow(() -> new NotFoundException("Работник не найден")));
        model.addAttribute("table", whs.getNotWorksDayInCalendar(whId, "2023-11-01", "2023-11-30"));

        return "workHistory/workHours";
    }


}
