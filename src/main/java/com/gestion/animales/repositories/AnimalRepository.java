package com.gestion.animales.repositories;

import com.gestion.animales.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository
    extends JpaRepository<Animal, Integer> {
}

