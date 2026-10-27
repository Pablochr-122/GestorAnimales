package com.gestion.animales.controllers;

import com.gestion.animales.models.Animal;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("editMode", false);
        return "animals/new";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        Animal animal = service.findById(id);

        if (animal == null) {
            return "redirect:/animals";
        }

        model.addAttribute("animal", animal);
        model.addAttribute("editMode", true);

        return "animals/new";
    }

    @PostMapping("/{id}/edit")
    public String edit(
            @PathVariable Integer id,
            @Valid @ModelAttribute("animal") Animal datos,
            BindingResult result,
            Model model) {

        Animal animal = service.findById(id);

        if (animal == null) {
            return "redirect:/animals";
        }

        if (result.hasErrors()) {
            // Restaurar el ID original y mantener los errores
            datos.setId(id);
            model.addAttribute("editMode", true);
            return "animals/new";
        }

        service.update(id, datos);

        return "redirect:/animals";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("animal") Animal animal,
            BindingResult result) {

        if (result.hasErrors()) {
            return "animals/new";
        }

        service.save(animal);
        return "redirect:/animals";
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/animals";
    }


}


