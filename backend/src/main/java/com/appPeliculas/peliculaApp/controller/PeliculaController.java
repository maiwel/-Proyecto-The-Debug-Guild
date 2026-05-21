package com.appPeliculas.peliculaApp.controller;

import com.appPeliculas.peliculaApp.model.Pelicula;
import com.appPeliculas.peliculaApp.repository.PeliculaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "http://localhost:4200")
public class PeliculaController {

    private final PeliculaRepository peliculaRepository;

    public PeliculaController(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    @GetMapping
    public List<Pelicula> getAll() {
        return peliculaRepository.findAllByOrderByTituloAsc();
    }

    @GetMapping("/{id}")
    public Pelicula getById(@PathVariable Integer id) {
        return peliculaRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Integer id) {
        peliculaRepository.deleteById(id);
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }
}