package ru.egar.myOrg.Animal;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@org.springframework.stereotype.Controller
@RequestMapping
@AllArgsConstructor
public class Controller {

    @GetMapping("/animal")
    public void newOrg() {

        return ;
    }


}
