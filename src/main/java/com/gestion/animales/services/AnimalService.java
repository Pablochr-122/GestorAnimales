package com.gestion.animales.services;

import org.springframework.stereotype.Service;
import com.gestion.animales.models.Animal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private final List<Animal> animals = new ArrayList<>();

    public AnimalService() {
        animals.add(new Animal(1, "Dodo", 30, true));
        animals.add(new Animal(2, "Tortuga", 100, false));
        animals.add(new Animal(3, "Lince", 15, false));
    }

    public List<Animal> findAll() { return animals; }

    public Animal findById(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }
}
