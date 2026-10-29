package com.animal.cadastro_animal.controller;

import com.animal.cadastro_animal.business.AnimalNaoEncontradoException;
import com.animal.cadastro_animal.business.AnimalService;
import com.animal.cadastro_animal.infrastructure.entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> salvarAnimal(@RequestBody Animal animal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.salvarAnimal(animal));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> listarAnimais(@RequestParam(required = false) String nome) {
        if (nome != null && !nome.isBlank()) {
            return ResponseEntity.ok(animalService.buscarAnimaisPorNome(nome));
        }
        return ResponseEntity.ok(animalService.listarAnimais());
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Animal>> filtrarAnimaisPorEspecie(@PathVariable String especie) {
        return ResponseEntity.ok(animalService.buscarAnimaisPorEspecie(especie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizarAnimalPorId(@PathVariable Integer id,
                                                       @RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.atualizarAnimalPorId(id, animal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnimalPorId(@PathVariable Integer id) {
        animalService.deletarAnimalPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Void> deletarAnimaisPorNome(@PathVariable String nome) {
        animalService.deletarAnimaisPorNome(nome);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "nome")
    public ResponseEntity<Void> deletarAnimaisPorNomeNaConsulta(@RequestParam String nome) {
        animalService.deletarAnimaisPorNome(nome);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(AnimalNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> tratarAnimalNaoEncontrado(AnimalNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", exception.getMessage()));
    }
}
