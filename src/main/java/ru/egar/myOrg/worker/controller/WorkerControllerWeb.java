package ru.egar.myOrg.worker.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egar.myOrg.worker.service.EmployPositionService;
import ru.egar.myOrg.worker.service.WorkerService;


@Controller
@RequestMapping("/worker")
@AllArgsConstructor
public class WorkerControllerWeb {
    private final WorkerService workerService;
    private final EmployPositionService empPosService;

    @GetMapping("/newWorker")
    public String newWorker() {
        System.out.println("22222222222222");
        return "workers/newWorker";
    }


    @GetMapping
    public String workers(Model model) {
        model.addAttribute("workers",workerService.getAll());

        System.out.println("");
        return "workers/workMain";
    }


}
