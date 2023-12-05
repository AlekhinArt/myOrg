package ru.egar.myOrg.worker.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.NotWorksDayService;
import ru.egar.myOrg.worker.service.WorkerService;
import ru.egar.myOrg.worker.service.impl.WorkerHistoryService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/wh")
@Tag(name = "История работы", description = "Взаимодействие с сущностью NotWorksDays")
@RequiredArgsConstructor
public class WorkHistoryController {
    @Qualifier("notWorksDayValidator")
    @Autowired
    private Validator nwdValidator;
    private final NotWorksDayService nwdService;
    private final WorkerHistoryService whService;
    private final EmployPositionService empService;
    private final WorkerService workerService;




    @InitBinder("notWorksDays")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(nwdValidator);
    }

    @GetMapping("/{orgId}/{whId}")
    public String getAllNotWorkDayByWh(@PathVariable Long whId, @PathVariable Long orgId, Model model) {
        log.info("getAllNotWorkDayByWh historyId {} ", whId);
        WorkHistory workHistory = whService.getById(whId)
                .orElseThrow(() -> new NotFoundException("История не найдена"));
        Collection<NotWorksDays> notWorksDays = nwdService.getAllByWhId(whId);
        model.addAttribute("ndws", notWorksDays);
        model.addAttribute("whId", whId);
        model.addAttribute("workerId", workHistory.getWorker().getId());
        model.addAttribute("orgId", orgId);
        model.addAttribute("sum", nwdService.getSumNotWorkDays(notWorksDays));
        model.addAttribute("dateNow", LocalDate.now());

        return "notWorksDays/notWorksDays";
    }

    @GetMapping("/{orgId}/{whId}/bt")
    public String getNotWorkDaysByType(@PathVariable Long orgId, @PathVariable Long whId,
                                       @RequestParam String typeOffDay, Model model,
                                       @RequestParam String start, @RequestParam String end) {
        log.info("getNotWorkDaysByType historyId {}, type {}, start {} , end {}", whId, typeOffDay, start, end);
        WorkHistory workHistory = whService.getById(whId)
                .orElseThrow(() -> new NotFoundException("История не найдена"));
        model.addAttribute("ndws", nwdService.notWorkDayByTypeAndDate(typeOffDay, whId, start, end));
        model.addAttribute("whId", whId);
        model.addAttribute("workerId", workHistory.getWorker().getId());
        model.addAttribute("orgId", orgId);
        model.addAttribute("sum", nwdService.getSumNotWorkDays(nwdService.notWorkDayByTypeAndDate(typeOffDay, whId, start, end)));
        model.addAttribute("dateNow", LocalDate.now());

        return "notWorksDays/notWorksDays";

    }


    @GetMapping("/{orgId}/create/{id}")
    public String createNotWorkDaysMVC(@PathVariable Long id, @PathVariable Long orgId, Model model) {
        log.info("createNotWorkDaysMVC historyId {} ", id);
        model.addAttribute("whs", whService.getById(id)
                .orElseThrow(() -> new NotFoundException("История не найдена")));
        model.addAttribute("maxTime", LocalDate.now());
        model.addAttribute("orgId", orgId);

        return "notWorksDays/newNWD";
    }


    @PostMapping("{orgId}/{whId}")
    public String saveNotWorkDay(@ModelAttribute @Valid NotWorksDays nwd, @PathVariable Long whId,
                                 @PathVariable Long orgId, BindingResult bindingResult,
                                 Model model) {
        log.info("Добавили не рабочий день {}, {}, {}, {},{}", nwd.getNwdId(), nwd.getStart(), nwd.getEnd(), nwd.getTypeDay(), nwd.getNote());
        if (bindingResult.hasErrors()) {
            log.info("------------ yes baby");
            return "redirect:/wh/" + orgId + "/" + whId;
        }
        nwdService.saveNotWorksDay(nwd, whId);

        return "redirect:/wh/" + orgId + "/" + whId;
    }

    @GetMapping("/{orgId}/{whId}/delete/{id}")
    public String deleteNotWorkDay(@PathVariable Long id,
                                   @PathVariable Long whId,
                                   @PathVariable Long orgId) {
        log.info("delete not work day id {}", id);
        nwdService.deleteById(id);

        return "redirect:/wh/" + orgId + "/" + whId;
    }

    @GetMapping("/{orgId}/{workerId}/delete/wh/{whId}")
    public String deleteWorkHistory(@PathVariable Long whId,
                                    @PathVariable Long workerId,
                                    @PathVariable Long orgId) {
        log.info("delete workHistory {}", whId);
        whService.deleteById(whId);
        whService.changeWorkerStatus(workerId);

        return "redirect:/worker/" + orgId + "/get/" + workerId;
    }

    @GetMapping("/{orgId}/{workerId}/edit/{whId}")
    public String layOffWorkerMVC(@PathVariable Long whId,
                                  @PathVariable Long workerId,
                                  @PathVariable Long orgId,
                                  Model model) {
        model.addAttribute("wh", whService.getById(whId)
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

        whService.layOffWorker(workHistory, whId);

        return "redirect:/worker/" + orgId + "/get/" + workerId;
    }

    @GetMapping("/{orgId}/{workerId}/create/")
    public String createWorkHistoryMVC(@PathVariable Long workerId,
                                       @PathVariable Long orgId,
                                       Model model) {
        model.addAttribute("employPositions", empService.getAll());
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
                                    @RequestParam String baseRate,
                                    @RequestParam String indexRate,
                                    Model model) {
        log.info("createWorkHistory workerId {}, orgId {}, wh.start {}, emplPosId {}, baseRate {}, indexRate {}",
                workerId, orgId, startWork, emplPosId, baseRate, indexRate);
        whService.createNewWorkHistory(workerId, startWork, emplPosId, baseRate, indexRate);

        return "redirect:/worker/" + orgId + "/get/" + workerId;
    }

    @GetMapping("/{orgId}/{workerId}/calendar")
    public String workHours(@PathVariable Long workerId,
                            @PathVariable Long orgId,
                            @RequestParam Long whId,
                            @RequestParam String year,
                            @RequestParam String month,
                            Model model) {
        log.info("{} {} {}", year, month, whId);
        LocalDate start = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 01);
        LocalDate end = start.plusDays(start.lengthOfMonth() - 1);
        log.info("start : {} , end : {}", start, end);

        model.addAttribute("workerId", workerId);
        model.addAttribute("orgId", orgId);
        model.addAttribute("month", start.getMonth().name());
        model.addAttribute("worker", workerService.getById(orgId).orElseThrow(() -> new NotFoundException("Работник не найден")));
        model.addAttribute("table", whService.getNotWorksDayInCalendar(whId,
                start.format(DateTimeFormatter.ISO_LOCAL_DATE), end.format(DateTimeFormatter.ISO_LOCAL_DATE)));
        model.addAttribute("paymentInfo", whService.getPaymentInfo(whId,
                start.format(DateTimeFormatter.ISO_LOCAL_DATE), end.format(DateTimeFormatter.ISO_LOCAL_DATE)));

        return "workHistory/workHours";
    }


}
