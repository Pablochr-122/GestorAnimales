package com.gestion.animales.controllers;

import com.gestion.animales.models.Animal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gestion.animales.services.AnimalService;

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
            return "redirect:/animals";
        }

        model.addAttribute("animal", animal);
        return "animals/detail";
    }

}


