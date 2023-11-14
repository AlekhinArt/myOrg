package ru.egar.myOrg.worker.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.model.WorkHistory;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;
import ru.egar.myOrg.worker.service.NotWorksDayService;
import ru.egar.myOrg.worker.service.impl.WorkerHistoryService;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/wh")
@Tag(name = "История работы", description = "Взаимодействие с сущностью NotWorksDAys")
@AllArgsConstructor
public class WorkHistoryController {

    private final NotWorksDayService nwds;
    private final WorkerHistoryService whs;


    @GetMapping("/{orgId}/{id}")
    public String getAllNotWorkDayByWh(@PathVariable Long id, @PathVariable Long orgId, Model model) {
        log.info("getAllNotWorkDayByWh historyId {} ", id);
        model.addAttribute("ndws", whs.getById(id)
                .orElseThrow(() -> new NotFoundException("История не найдена")).getNotWorksDays());
        model.addAttribute("whId", id);
        model.addAttribute("workerId", whs.getById(id).orElseThrow().getWorker().getId());
        model.addAttribute("orgId", orgId);

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

        WorkHistory workHistory = whs.getById(whId).orElseThrow();
        workHistory.getNotWorksDays().add(nwd);
        whs.create(workHistory);
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

}
