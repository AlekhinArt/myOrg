package ru.egar.myOrg.organization.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egar.myOrg.organization.service.OrganizationService;

@Slf4j
@Controller
@RequestMapping("/organization")
@Tag(name = "Организация", description = "Взаимодействие с сущностью организация")
@AllArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;





}
