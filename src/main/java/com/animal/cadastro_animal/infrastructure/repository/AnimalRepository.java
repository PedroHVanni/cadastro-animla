package com.animal.cadastro_animal.infrastructure.repository;

import com.animal.cadastro_animal.infrastructure.entity.Animal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Optional<Animal> findByEspecie(String especie);

    @Transactional
    void deleteByEspecie(String especie)
}
