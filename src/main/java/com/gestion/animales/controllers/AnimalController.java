package com.gestion.animales.controllers;

import com.gestion.animales.models.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.gestion.animales.services.AnimalService;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService service;
    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("animals", service.findAll());
        return "animals/index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Animal animal = service.findById(id);

        if (animal == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Animal no encontrado"
            );
        }

        model.addAttribute("animal", animal);
        return "animals/detail";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "animals/new";
    }

    @PostMapping
    public String create(@ModelAttribute Animal animal) {
        service.save(animal);
        return "redirect:/animals";
    }
}


