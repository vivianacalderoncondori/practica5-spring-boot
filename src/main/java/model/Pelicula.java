package com.practica.peliculas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "peliculas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 200, message = "El título no puede superar los 200 caracteres")
    @Column(nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "El director no puede estar vacío")
    @Size(max = 150, message = "El director no puede superar los 150 caracteres")
    @Column(nullable = false, length = 150)
    private String director;

    @NotNull(message = "El año de lanzamiento es obligatorio")
    @Min(value = 1888, message = "El año mínimo válido es 1888")
    @Max(value = 2100, message = "El año no puede ser mayor a 2100")
    @Column(name = "anio_lanzamiento", nullable = false)
    private Integer anioLanzamiento;

    @NotBlank(message = "El género no puede estar vacío")
    @Size(max = 80, message = "El género no puede superar los 80 caracteres")
    @Column(nullable = false, length = 80)
    private String genero;

    @Size(max = 1000, message = "La sinopsis no puede superar los 1000 caracteres")
    @Column(length = 1000)
    private String sinopsis;
}
