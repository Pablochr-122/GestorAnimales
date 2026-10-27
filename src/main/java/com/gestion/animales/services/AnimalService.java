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

    public Animal save(Animal animal) {
        int nextId = animals.stream()
                .mapToInt(Animal::getId)
                .max()
                .orElse(0) + 1;

        animal.setId(nextId);
        animals.add(animal);

        return animal;
    }

    public void update(int id, Animal datos) {
        Animal animal = findById(id);

        if (animal != null) {
            animal.setNombre(datos.getNombre());
            animal.setVidaMedia(datos.getVidaMedia());
            animal.setExtinto(datos.isExtinto());
        }
    }

    public void deleteById(int id) {
        animals.removeIf(animal -> animal.getId() == id);
    }

}
