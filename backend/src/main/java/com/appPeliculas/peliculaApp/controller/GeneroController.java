package com.appPeliculas.peliculaApp.controller;

import com.appPeliculas.peliculaApp.model.Genero;
import com.appPeliculas.peliculaApp.repository.GeneroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@CrossOrigin(origins = "http://localhost:4200")
public class GeneroController {

    private final GeneroRepository generoRepository;

    public GeneroController(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @GetMapping
    public List<Genero> getAll() {
        return generoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Genero getById(@PathVariable Integer id) {
        return generoRepository.findById(id).orElseThrow();
    }
}