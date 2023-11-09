package ru.egar.myOrg.worker.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkerService;


@Controller
@RequestMapping("/worker")
@AllArgsConstructor
public class WorkerControllerWeb {
    private final WorkerService workerService;
    private final EmployPositionService empPosService;

    @GetMapping("/newWorker/{id}")
    public String newWorker(@PathVariable Long id, Model model) {
        model.addAttribute("employPositions", empPosService.getPositionName());
        model.addAttribute("orgId", id);
        return "workers/newWorker";
    }

    // TODO: 07.11.2023 поставить отлов ошибки на более нижний уровень
    @GetMapping("/org/{id}")
    public String workers(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("workers",workerService.showWorkers(id));
        } catch (Exception e){
            model.addAttribute("employPositions", empPosService.getPositionName());
            return "workers/newWorker";
        }
        return "workers/workMain";
    }


}
