package com.gestion.animales.services;
import com.gestion.animales.models.Animal;
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

    public List<Animal> findAll() {
        return repo.findAll();
    }

    public Optional<Animal> findById(Integer id) {
        return repo.findById(id);
    }

    public void save(Animal animal) {
        repo.save(animal);
    }

    public void update(Integer id, Animal datos) {
        Optional<Animal> resultado = repo.findById(id);

        if (resultado.isPresent()) {
            Animal animal = resultado.get();

            animal.setNombre(datos.getNombre());
            animal.setVidaMedia(datos.getVidaMedia());
            animal.setExtinto(datos.isExtinto());

            repo.save(animal);
        }
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
