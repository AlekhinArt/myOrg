package ru.egar.myOrg.worker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return "redirect:/empl";
    }

    @GetMapping("/newEmplPos")
    public String newEmplPos() {
        return "employPos/newEmplPos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("empls", empPosService.getAll());
        empPosService.deleteById(id);
        return "redirect:/empl";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("empl", empPosService.getById(id).orElseThrow());
        return "employPos/emplsUpdate";
    }

    @GetMapping
    public String emplsMain(Model model) {
        model.addAttribute("empls", empPosService.getAll());
        return "employPos/emplsMain";
    }


}
