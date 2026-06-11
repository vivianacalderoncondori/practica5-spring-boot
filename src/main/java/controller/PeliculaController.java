package com.practica.peliculas.controller;

import com.practica.peliculas.model.Pelicula;
import com.practica.peliculas.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@Tag(name = "Películas", description = "CRUD completo para la gestión de películas")
public class PeliculaController {

    private final PeliculaService service;

    public PeliculaController(PeliculaService service) {
        this.service = service;
    }

    @Operation(summary = "Crear una nueva película")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Película creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Pelicula> crear(@Valid @RequestBody Pelicula pelicula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(pelicula));
    }

    @Operation(summary = "Obtener todas las películas")
    @GetMapping
    public ResponseEntity<List<Pelicula>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @Operation(summary = "Obtener una película por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Película encontrada"),
            @ApiResponse(responseCode = "404", description = "Película no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> buscarPorId(
            @Parameter(description = "ID de la película") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Actualizar una película existente")
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Pelicula datos) {
        return ResponseEntity.ok(service.actualizar(id, datos));
    }

    @Operation(summary = "Eliminar una película")
    @ApiResponse(responseCode = "204", description = "Película eliminada exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar películas por género")
    @GetMapping("/buscar/genero")
    public ResponseEntity<List<Pelicula>> porGenero(@RequestParam String valor) {
        return ResponseEntity.ok(service.buscarPorGenero(valor));
    }

    @Operation(summary = "Buscar películas por director")
    @GetMapping("/buscar/director")
    public ResponseEntity<List<Pelicula>> porDirector(@RequestParam String valor) {
        return ResponseEntity.ok(service.buscarPorDirector(valor));
    }

    @Operation(summary = "Buscar películas por año")
    @GetMapping("/buscar/anio")
    public ResponseEntity<List<Pelicula>> porAnio(@RequestParam Integer valor) {
        return ResponseEntity.ok(service.buscarPorAnio(valor));
    }
}
