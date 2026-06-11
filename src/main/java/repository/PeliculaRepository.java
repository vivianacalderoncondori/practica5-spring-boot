package com.practica.peliculas.repository;

import com.practica.peliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    List<Pelicula> findByGeneroIgnoreCase(String genero);

    List<Pelicula> findByDirectorContainingIgnoreCase(String director);

    List<Pelicula> findByAnioLanzamiento(Integer anio);
}
