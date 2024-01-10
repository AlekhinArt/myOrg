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

    @GetMapping("/cats")
    public List<Cat> getCats() {
        return animalGo.findAllCats();
    }

    @GetMapping("/dogs")
    public List<Dog> getDogs() {
        return animalGo.findAllDogs();
    }
}
