package com.animal.cadastro_animal.business;

import com.animal.cadastro_animal.infrastructure.entity.Animal;
import com.animal.cadastro_animal.infrastructure.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public Animal salvarAnimal(Animal animal) {
        animal.setId(null);
        return repository.saveAndFlush(animal);
    }

    public List<Animal> listarAnimais() {
        return repository.findAll();
    }

    public Animal buscarAnimalPorEspecie(String especie) {
        return repository.findByEspecie(especie).orElseThrow(
                () -> new RuntimeException("Espécie não encontrado")
        );
    }

    @Transactional
    public void deletarAnimalPorEspecie(String especie) {
        repository.deleteByEspecie(especie);
    }

    public Animal atualizarAnimalPorEspecie(String especie, Animal animal) {
        Animal AnimalEntity = buscarAnimalPorEspecie(especie);
        Animal animalAtualizado = Animal.builder()
                .nome(animal.getNome() != null ? animal.getNome() : UsuarioEntity.getNome())
                .especie(animal.getEspecie() != null ? animal.getEspecie() : UsuarioEntity.getEspecie())
                .raca(animal.getRaca() != null ? animal.getRaca() : UsuarioEntity.getRaca())
                .idade(animal.getIdade() != null ? animal.getIdade() : UsuarioEntity.getIdade())
        return repository.saveAndFlush(UsuarioEntity);
    }
}
