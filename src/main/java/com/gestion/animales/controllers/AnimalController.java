package com.gestion.animales.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}


