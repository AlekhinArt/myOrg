package ru.egar.myOrg.organization.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.organization.service.OrganizationService;

@Slf4j
@Controller
@RequestMapping
@Tag(name = "Организация", description = "Взаимодействие с сущностью организация")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @Operation(summary = "Добавление",
            description = "Добавляем организацию")
    @PostMapping("/organization")
    public String create(@ModelAttribute("organization") OrganizationDto organizationDto, Model model) {
        log.info("Create organization: {}, {}, {}, {}, {}", organizationDto.getName(), organizationDto.getInn(),
                organizationDto.getOgrn(), organizationDto.getAddress(),
                organizationDto.getPhoneNumber(), organizationDto.getZip());
        return "redirect:/organization/" + organizationService.create(organizationDto).getId();
    }

    @GetMapping("/organization/newOrg")
    public String newOrg() {

        return "organization/newOrg";
    }


    @Operation(summary = "Удаление",
            description = "Удаление организацию")
    @DeleteMapping("/organization/{id}")
    public void deleteOrg(@PathVariable Long id) {
        log.info("delete organization with id {}", id);
        organizationService.deleteById(id);
    }

    @Operation(summary = "Получение",
            description = "Получение организации по id")
    @GetMapping("/organization/{id}")
    public String getById(@PathVariable Long id, Model model) {
        log.info("getById organization with id {}", id);
        model.addAttribute("org", organizationService.getById(id)
                .orElseThrow(() -> new NotFoundException("Организация не найдена")));
        return "organization/orgMain";

    }

    @Operation(summary = "Получение работников",
            description = "Получение работников организации по id")
    @GetMapping("/organization/workers/{id}")
    public String getWorkers(@PathVariable Long id, Model model) {
        log.info("getWorkers organization with id {}", id);
        model.addAttribute("workers", organizationService.getWorkers(id));
        model.addAttribute("org", id);
        return "workers/workMain";
    }

    @GetMapping("/organization/upd/{id}")
    public String updateOrg(@PathVariable Long id, Model model) {
        model.addAttribute("organization", organizationService.getById(id)
                .orElseThrow(() -> new NotFoundException("Организация не найдена")));
        return "organization/updOrg";
    }

    @GetMapping("/")
    public String getAllOrg(Model model) {
        model.addAttribute("orgs", organizationService.getAll());
        return "index";
    }

}
