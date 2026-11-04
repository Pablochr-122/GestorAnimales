
package com.gestion.animales.services;

import com.gestion.animales.models.Animal;
import com.gestion.animales.models.Clase;
import com.gestion.animales.repositories.AnimalRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository repo;

    public AnimalService(AnimalRepository repo) {
        this.repo = repo;
    }

    // Obtener todos los animales
    public List<Animal> findAll() {
        return repo.findAll();
    }

    // Buscar animal por ID
    public Optional<Animal> findById(Integer id) {
        return repo.findById(id);
    }

    // Guardar animal
    public void save(Animal animal) {
        repo.save(animal);
    }

    // Actualizar animal
    public void update(Integer id, Animal datos) {
        Optional<Animal> resultado = repo.findById(id);

        if (resultado.isPresent()) {
            Animal animal = resultado.get();

            animal.setNombre(datos.getNombre());
            animal.setVidaMedia(datos.getVidaMedia());
            animal.setExtinto(datos.isExtinto());
            animal.setClase(datos.getClase());

            repo.save(animal);
        }
    }

    // Eliminar animal
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    // Buscar animales por clase
    public List<Animal> findByClaseId(Integer claseId) {
        return repo.findByClase_Id(claseId);
    }
}
