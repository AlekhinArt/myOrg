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


    @GetMapping("/{id}")
    public String getAllNotWorkDayByWh(@PathVariable Long id, Model model) {
        model.addAttribute("ndws", nwds.getAllByWhId(id));
        model.addAttribute("whId", id);

        return "notWorksDays/notWorksDays";
    }

    @GetMapping("/create/{id}")
    public String createNotWorkDaysMVC(@PathVariable Long id, Model model) {

        model.addAttribute("whs", whs.getById(id)
                .orElseThrow(() -> new NotFoundException("История не найдена")));
        model.addAttribute("maxTime", LocalDate.now());
        return "notWorksDays/newNWD";
    }


    @PostMapping("/{id}")
    public String saveNotWorkDay(@ModelAttribute NotWorksDays nwd, @PathVariable Long id, Model model){
//        model.addAttribute("ndws", nwds.getAllByWhId(nwd.getWorkHistory().getId()));
//       log.info("Добавили не рабочий день {}, {}, {}, {},{}", nwd.getWorkHistory().getId(), nwd.getStart(), nwd.getEnd(), nwd.getTypeDay(), nwd.getNote());
//       nwd.getWorkHistory().setId(id);
//       nwds.create(nwd);
        WorkHistory workHistory =whs.getById(id).orElseThrow();
        workHistory.getNotWorksDays().add(nwd);
        whs.create(workHistory);
       log.info("Сюда зашли");
        return "notWorksDays/notWorksDays";
    }







}
