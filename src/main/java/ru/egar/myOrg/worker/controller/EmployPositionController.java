package ru.egar.myOrg.worker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egar.myOrg.worker.dto.EmployPositionDto;
import ru.egar.myOrg.worker.service.EmployPositionService;

@Slf4j
@Controller
@RequestMapping("/empl")
@Tag(name = "Должность", description = "Взаимодействие с сущностью должность")
@AllArgsConstructor
public class EmployPositionController {


    private final EmployPositionService empPosService;


    @Operation(summary = "Добавление",
            description = "Добавляем должность")
    @PostMapping
    public String create(@ModelAttribute("emplPos") EmployPositionDto employPositionDto, Model model) {
        log.info("Create employ position: {}, {}, {}, {}, {}", employPositionDto.getPosition(), employPositionDto.getJobDescription());
        empPosService.create(employPositionDto);

        return "redirect:/";
    }
    @GetMapping("/newEmplPos")
    public String newOrg() {

        return "employPos/newEmplPos";
    }

    @GetMapping
    public String workers(Model model) {
        model.addAttribute("empls",empPosService.getAll());

        return "employPos/emplsMain";
    }


}
