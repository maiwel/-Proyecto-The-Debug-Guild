package com.appPeliculas.peliculaApp.controller;

import com.appPeliculas.peliculaApp.dto.GeneroDTO;
import com.appPeliculas.peliculaApp.service.GeneroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@CrossOrigin(origins = "*") // Permite la conexión total con Angular sin bloqueos de CORS
public class GeneroController {

    private final GeneroService generoService;

    // Inyección por constructor (Limpio y óptimo)
    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    // 1. GET /api/generos -> Listar todos
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> obtenerTodos() {
        return ResponseEntity.ok(generoService.obtenerTodos());
    }

    // 2. GET /api/generos/{id} -> Buscar uno por ID
    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(generoService.obtenerPorId(id));
    }

    // 3. POST /api/generos -> Crear un género
    @PostMapping
    public ResponseEntity<GeneroDTO> guardar(@RequestBody GeneroDTO generoDTO) {
        return ResponseEntity.ok(generoService.guardar(generoDTO));
    }

    // 4. PUT /api/generos/{id} -> Actualizar un género
    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> actualizar(@PathVariable Integer id, @RequestBody GeneroDTO generoDTO) {
        return ResponseEntity.ok(generoService.actualizar(id, generoDTO));
    }

    // 5. DELETE /api/generos/{id} -> Borrar un género
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        generoService.borrar(id);
        return ResponseEntity.noContent().build(); // Devuelve un estado 204 sin cuerpo
    }
}