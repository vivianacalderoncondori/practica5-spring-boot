package com.practica.peliculas.exception;

public class PeliculaNotFoundException extends RuntimeException {

    public PeliculaNotFoundException(Long id) {
        super("Película con id " + id + " no encontrada.");
    }
}
