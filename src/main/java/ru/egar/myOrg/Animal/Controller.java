package ru.egar.myOrg.Animal;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class Controller {

    private final AnimalGo animalGo;

    private final BaseMapper mapper;

    @GetMapping("/cats")
    public List<Response> getCats() {
        return mapper.toDto(animalGo.findAllCats());
    }

    @GetMapping("/dogs")
    public List<Response> getDogs() {
        return mapper.toDto(animalGo.findAllDogs());
    }
}
