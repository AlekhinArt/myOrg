package ru.egar.myOrg.organization.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.organization.dto.OrganizationDto;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.organization.service.OrganizationService;

@Slf4j
@Controller
@RequestMapping("/organization")
@Tag(name = "Организация", description = "Взаимодействие с сущностью организация")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @Operation(summary = "Добавление",
            description = "Добавляем организацию")
    @PostMapping
    public String create(@ModelAttribute("organization") OrganizationDto organizationDto, Model model) {
        log.info("Create organization: {}, {}, {}, {}, {}",organizationDto.getName(), organizationDto.getInn(),
                organizationDto.getOgrn(), organizationDto.getAddress(),
                organizationDto.getPhoneNumber(), organizationDto.getZip());

         organizationService.create(organizationDto);
         model.addAttribute("orgName", organizationDto.getName());
                return "redirect:/";
    }

    @GetMapping("/newOrg")
    public String newOrg(Model model) {
        model.addAttribute("orgName1", organizationService.getById(1L));
        return "operations/newOrg";
    }


    @Operation(summary = "Удаление",
            description = "Удаление организацию")
    @DeleteMapping("/{id}")
    public void deleteOrg(@PathVariable Long id) {
        log.info("delete organization with id {}", id);
        organizationService.deleteById(id);
    }

    @Operation(summary = "Получение",
            description = "Получение организации по id")
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id) {
        log.info("getById organization with id {}", id);
        organizationService.getById(id);
        return "";

    }

    @Operation(summary = "Получение работников",
            description = "Получение работников организации по id")
    @GetMapping("/worker/{id}")
    public String getWorkers(@PathVariable Long id) {
        log.info("getWorkers organization with id {}", id);
        organizationService.getWorkers(id);
        return "";
    }

    @GetMapping("/upd/{id}")
    public String updateOrg(@PathVariable Long id,
                            @RequestParam String name,
                            @RequestParam String inn,
                            @RequestParam String ogrn,
                            @RequestParam String address,
                            @RequestParam String phoneNumber) {
        log.info("Create organization with id {}: {}, {}, {}, {}, {}", id, name, inn, ogrn, address, phoneNumber);
        organizationService.updateById(id, OrganizationDto.builder()
                .name(name)
                .inn(inn)
                .ogrn(ogrn)
                .address(address)
                .phoneNumber(phoneNumber)
                .build());
        return "";

    }



//    @GetMapping("/newOrg")
//    public String abc (Model model){
////        organizationService.getById(1L);
//
//        return "operations/newOrg";
//    }

}
