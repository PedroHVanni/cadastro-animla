package com.animal.cadastro_animal.business;

import com.animal.cadastro_animal.infrastructure.entity.Animal;
import com.animal.cadastro_animal.infrastructure.repository.AnimalRepository;
import org.springframework.stereotype.Service;

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

    public Animal buscarAnimalPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new AnimalNaoEncontradoException(id)
        );
    }

    public Animal atualizarAnimalPorId(Integer id, Animal animal) {
        Animal animalExistente = buscarAnimalPorId(id);
        animalExistente.setNome(animal.getNome() != null ? animal.getNome() : animalExistente.getNome());
        animalExistente.setEspecie(animal.getEspecie() != null ? animal.getEspecie() : animalExistente.getEspecie());
        animalExistente.setRaca(animal.getRaca() != null ? animal.getRaca() : animalExistente.getRaca());
        animalExistente.setIdade(animal.getIdade() != null ? animal.getIdade() : animalExistente.getIdade());
        return repository.saveAndFlush(animalExistente);
    }

    public void deletarAnimalPorId(Integer id) {
        repository.delete(buscarAnimalPorId(id));
    }

    public List<Animal> buscarAnimaisPorEspecie(String especie) {
        return repository.findAllByEspecieIgnoreCase(especie);
    }

    public List<Animal> buscarAnimaisPorNome(String nome) {
        return repository.findAllByNomeContainingIgnoreCase(nome.trim());
    }

}
