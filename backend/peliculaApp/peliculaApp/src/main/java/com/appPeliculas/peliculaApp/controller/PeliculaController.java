package com.appPeliculas.peliculaApp.controller;

import com.appPeliculas.peliculaApp.dto.PeliculaDTO;
import com.appPeliculas.peliculaApp.service.PeliculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "*") // Permite la conexión total con Angular sin bloqueos de CORS
public class PeliculaController {

    private final PeliculaService peliculaService;

    // Inyección por constructor (Óptimo y recomendado)
    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    // 1. GET /api/peliculas -> Listar todas
    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> obtenerTodas() {
        return ResponseEntity.ok(peliculaService.obtenerTodas());
    }

    // 2. GET /api/peliculas/{id} -> Buscar una por ID
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(peliculaService.obtenerPorId(id));
    }

    // 3. POST /api/peliculas -> Crear una película
    @PostMapping
    public ResponseEntity<PeliculaDTO> guardar(@RequestBody PeliculaDTO peliculaDTO) {
        return ResponseEntity.ok(peliculaService.guardar(peliculaDTO));
    }

    // 4. PUT /api/peliculas/{id} -> Actualizar una película
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizar(@PathVariable Integer id, @RequestBody PeliculaDTO peliculaDTO) {
        return ResponseEntity.ok(peliculaService.actualizar(id, peliculaDTO));
    }

    // 5. DELETE /api/peliculas/{id} -> Borrar una película
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        peliculaService.borrar(id);
        return ResponseEntity.noContent().build(); // Devuelve un estado 204 sin cuerpo, ideal para borrados
    }
}