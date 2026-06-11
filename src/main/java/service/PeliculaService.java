package com.practica.peliculas.service;

import com.practica.peliculas.exception.PeliculaNotFoundException;
import com.practica.peliculas.model.Pelicula;
import com.practica.peliculas.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {

    private final PeliculaRepository repository;

    public PeliculaService(PeliculaRepository repository) {
        this.repository = repository;
    }

    public Pelicula crear(Pelicula pelicula) {
        return repository.save(pelicula);
    }

    public List<Pelicula> listarTodas() {
        return repository.findAll();
    }

    public Pelicula buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula actualizar(Long id, Pelicula datos) {
        Pelicula existente = buscarPorId(id);
        existente.setTitulo(datos.getTitulo());
        existente.setDirector(datos.getDirector());
        existente.setAnioLanzamiento(datos.getAnioLanzamiento());
        existente.setGenero(datos.getGenero());
        existente.setSinopsis(datos.getSinopsis());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public List<Pelicula> buscarPorGenero(String genero) {
        return repository.findByGeneroIgnoreCase(genero);
    }

    public List<Pelicula> buscarPorDirector(String director) {
        return repository.findByDirectorContainingIgnoreCase(director);
    }

    public List<Pelicula> buscarPorAnio(Integer anio) {
        return repository.findByAnioLanzamiento(anio);
    }
}