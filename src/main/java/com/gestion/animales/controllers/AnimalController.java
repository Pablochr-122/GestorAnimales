
package com.gestion.animales.controllers;

import com.gestion.animales.models.Animal;
import com.gestion.animales.models.Clase;
import com.gestion.animales.services.AnimalService;
import com.gestion.animales.repositories.ClaseRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService service;
    private final ClaseRepository claseRepo;

    public AnimalController(AnimalService service, ClaseRepository claseRepo) {
        this.service = service;
        this.claseRepo = claseRepo;
    }

    // Mostrar todos los animales
    @GetMapping
    public String index(Model model) {
        model.addAttribute("animals", service.findAll());
        return "animals/index";
    }

    // Mostrar detalles
    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {

        Optional<Animal> resultado = service.findById(id);

        if (resultado.isEmpty()) {
            return "redirect:/animals";
        }

        model.addAttribute("animal", resultado.get());
        return "animals/detail";
    }

    // Mostrar formulario de creación
    @GetMapping("/new")
    public String createForm(Model model) {

        model.addAttribute("animal", new Animal());
        model.addAttribute("editMode", false);
        model.addAttribute("clases", claseRepo.findAll());
        model.addAttribute("claseId", null);

        return "animals/new";
    }

    // Crear animal
    @PostMapping
    public String create(
            @Valid @ModelAttribute("animal") Animal animal,
            BindingResult result,
            @RequestParam(required = false) Integer claseId,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("editMode", false);
            model.addAttribute("clases", claseRepo.findAll());
            model.addAttribute("claseId", claseId);
            return "animals/new";
        }

        if (claseId != null) {
            Optional<Clase> clase = claseRepo.findById(claseId);

            if (clase.isEmpty()) {
                model.addAttribute("editMode", false);
                model.addAttribute("clases", claseRepo.findAll());
                model.addAttribute("claseId", null);
                model.addAttribute("errorClase", "La clase seleccionada no existe.");
                return "animals/new";
            }

            animal.setClase(clase.get());
        }

        service.save(animal);

        return "redirect:/animals";
    }

    // Mostrar formulario de edición
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {

        Optional<Animal> resultado = service.findById(id);

        if (resultado.isEmpty()) {
            return "redirect:/animals";
        }

        Animal animal = resultado.get();

        model.addAttribute("animal", animal);
        model.addAttribute("editMode", true);
        model.addAttribute("clases", claseRepo.findAll());
        model.addAttribute(
                "claseId",
                animal.getClase() != null ? animal.getClase().getId() : null
        );

        return "animals/new";
    }

    // Procesar edición
    @PostMapping("/{id}/edit")
    public String edit(
            @PathVariable Integer id,
            @Valid @ModelAttribute("animal") Animal datos,
            BindingResult result,
            @RequestParam(required = false) Integer claseId,
            Model model) {

        Optional<Animal> resultado = service.findById(id);

        if (resultado.isEmpty()) {
            return "redirect:/animals";
        }

        if (result.hasErrors()) {
            datos.setId(id);
            model.addAttribute("editMode", true);
            model.addAttribute("clases", claseRepo.findAll());
            model.addAttribute("claseId", claseId);
            return "animals/new";
        }

        if (claseId != null) {
            Optional<Clase> clase = claseRepo.findById(claseId);

            if (clase.isEmpty()) {
                datos.setId(id);
                model.addAttribute("editMode", true);
                model.addAttribute("clases", claseRepo.findAll());
                model.addAttribute("claseId", null);
                model.addAttribute("errorClase", "La clase seleccionada no existe.");
                return "animals/new";
            }

            datos.setClase(clase.get());

        } else {
            datos.setClase(null);
        }

        service.update(id, datos);

        return "redirect:/animals";
    }

    // Eliminar animal
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/animals";
    }

    // Mostrar animales de una clase
    @GetMapping("/class/{claseId}")
    public String animalsByClass(
            @PathVariable Integer claseId,
            Model model) {

        Optional<Clase> resultado = claseRepo.findById(claseId);

        if (resultado.isEmpty()) {
            return "redirect:/animals";
        }

        model.addAttribute("animals", service.findByClaseId(claseId));
        model.addAttribute("clase", resultado.get());

        return "animals/index";
    }
}
