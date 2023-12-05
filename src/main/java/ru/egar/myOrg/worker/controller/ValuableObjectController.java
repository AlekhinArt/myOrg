package ru.egar.myOrg.worker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.worker.model.enumerated.TypeOfValue;
import ru.egar.myOrg.worker.model.ValuableObject;
import ru.egar.myOrg.worker.service.ValuableObjectsService;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/obj")
@Tag(name = "Материальные ценности", description = "Взаимодействие с сущностью материальные ценности")
@AllArgsConstructor
public class ValuableObjectController {

    private final ValuableObjectsService voService;


    @Operation(summary = "Добавление",
            description = "Добавляем мат. ценность")
    @PostMapping("/{orgId}")
    public String create(@ModelAttribute @Valid ValuableObject vo, @PathVariable Long orgId, Model model) {
        log.info("Create worker: {}, {}, {}, {}",
                vo.getName(),
                vo.getPrice(),
                vo.getDateOfPurchase(),
                vo.getIsUse());
        voService.create(vo, orgId);
        return "redirect:/obj/get/" + orgId;
    }

    @GetMapping("/new/{orgId}")
    public String createMVC(@PathVariable Long orgId, Model model) {
        model.addAttribute("orgId", orgId);
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("types", TypeOfValue.values());
        return "valuableObject/createValuableObject";
    }

    @GetMapping("/get/{orgId}")
    public String getAllVo(@PathVariable Long orgId, Model model) {
        log.info("get all V object orgId {}", orgId);
        model.addAttribute("orgId", orgId);
        model.addAttribute("objs", voService.getAllByOrgId(orgId));
        model.addAttribute("types", TypeOfValue.values());
        return "valuableObject/allValuableObject";
    }

    @GetMapping("/{orgId}/search")
    public String searchBy(@PathVariable Long orgId, Model model,
                           @RequestParam String word,
                           @RequestParam String type) {
        log.info("searchBy orgId {}, word {}, type {}", orgId, word, type);
        model.addAttribute("orgId", orgId);
        model.addAttribute("objs", voService.searchBy(orgId, word, type));
        model.addAttribute("types", TypeOfValue.values());
        return "valuableObject/allValuableObject";
    }


}
