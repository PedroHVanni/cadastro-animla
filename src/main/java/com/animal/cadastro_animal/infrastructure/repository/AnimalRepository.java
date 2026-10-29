package com.animal.cadastro_animal.infrastructure.repository;

import com.animal.cadastro_animal.infrastructure.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findAllByEspecieIgnoreCase(String especie);

    List<Animal> findAllByNomeContainingIgnoreCase(String nome);

    List<Animal> findAllByNomeIgnoreCase(String nome);

}
