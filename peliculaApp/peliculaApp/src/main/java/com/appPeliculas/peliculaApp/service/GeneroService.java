package com.appPeliculas.peliculaApp.service;

import com.appPeliculas.peliculaApp.domain.Genero;
import com.appPeliculas.peliculaApp.dto.GeneroDTO;
import com.appPeliculas.peliculaApp.repository.GeneroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    // Inyección por constructor (Limpio y óptimo)
    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    // 1. GET /api/generos -> Listar todos
    @Transactional(readOnly = true)
    public List<GeneroDTO> obtenerTodos() {
        return generoRepository.findAll()
                .stream()
                .map(genero -> new GeneroDTO(genero.getId(), genero.getNombre()))
                .collect(Collectors.toList());
    }

    // 2. GET /api/generos/{id} -> Buscar uno por ID
    @Transactional(readOnly = true)
    public GeneroDTO obtenerPorId(Integer id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El género con ID " + id + " no existe"));
        return new GeneroDTO(genero.getId(), genero.getNombre());
    }

    // 3. POST /api/generos -> Crear un nuevo género
    @Transactional
    public GeneroDTO guardar(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setNombre(generoDTO.nombre());
        
        Genero guardado = generoRepository.save(genero);
        return new GeneroDTO(guardado.getId(), guardado.getNombre());
    }

    // 4. PUT /api/generos/{id} -> Actualizar un género existente
    @Transactional
    public GeneroDTO actualizar(Integer id, GeneroDTO generoDTO) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El género con ID " + id + " no existe"));
        
        genero.setNombre(generoDTO.nombre());
        
        Genero actualizado = generoRepository.save(genero);
        return new GeneroDTO(actualizado.getId(), actualizado.getNombre());
    }

    // 5. DELETE /api/generos/{id} -> Borrar un género
    @Transactional
    public void borrar(Integer id) {
        if (!generoRepository.existsById(id)) {
            throw new IllegalArgumentException("El género con ID " + id + " no existe");
        }
        generoRepository.deleteById(id);
    }
}