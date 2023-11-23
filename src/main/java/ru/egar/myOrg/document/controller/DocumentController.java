package ru.egar.myOrg.document.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egar.myOrg.document.dto.PassportDto;
import ru.egar.myOrg.document.service.DocumentService;

@Slf4j
@Controller
@RequestMapping("/doc")
@Tag(name = "Документы", description = "Обработка различных документов")
@AllArgsConstructor
public class DocumentController {
    private final DocumentService ds;

    @GetMapping("/{orgId}/{workerId}/{docTypeId}")
    private String updPassportMVC(@PathVariable Long workerId,
                                  @PathVariable Long orgId,
                                  @PathVariable Long docTypeId, Model model) {
        model.addAttribute("oldPassport", ds.findByWorkerIdAndActualTrue(workerId));
        model.addAttribute("workerId", workerId);
        model.addAttribute("orgId", orgId);
        return "documents/updDocument";
    }

    @PostMapping("/{orgId}/{workerId}")
    private String updPassport(@PathVariable Long workerId,
                               @PathVariable Long orgId,
                               @ModelAttribute PassportDto passport,
                               @RequestParam String whatDo,
                               Model model) {
        if (Boolean.valueOf(whatDo)){

        }else {

        }

        return "redirect:/worker/" + orgId + "/get/" + workerId;
    }

}
