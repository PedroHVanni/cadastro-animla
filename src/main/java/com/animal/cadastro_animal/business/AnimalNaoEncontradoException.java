package com.animal.cadastro_animal.business;

public class AnimalNaoEncontradoException extends RuntimeException {

    public AnimalNaoEncontradoException(Integer id) {
        super("Animal com id " + id + " não encontrado");
    }
}
