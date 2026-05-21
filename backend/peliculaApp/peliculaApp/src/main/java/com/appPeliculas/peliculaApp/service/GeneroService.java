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

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Transactional(readOnly = true)
    public List<GeneroDTO> obtenerTodos() {
        return generoRepository.findAll()
                .stream()
                .map(genero -> new GeneroDTO(genero.getId(), genero.getNombre()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GeneroDTO obtenerPorId(Integer id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El género con ID " + id + " no existe"));
        return new GeneroDTO(genero.getId(), genero.getNombre());
    }

    @Transactional
    public GeneroDTO guardar(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        // CAMBIADO: Usamos getNombre() en lugar de nombre()
        genero.setNombre(generoDTO.getNombre());
        
        Genero guardado = generoRepository.save(genero);
        return new GeneroDTO(guardado.getId(), guardado.getNombre());
    }

    @Transactional
    public GeneroDTO actualizar(Integer id, GeneroDTO generoDTO) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El género con ID " + id + " no existe"));
        
        // CAMBIADO: Usamos getNombre() en lugar de nombre()
        genero.setNombre(generoDTO.getNombre());
        
        Genero actualizado = generoRepository.save(genero);
        return new GeneroDTO(actualizado.getId(), actualizado.getNombre());
    }

    @Transactional
    public void borrar(Integer id) {
        if (!generoRepository.existsById(id)) {
            throw new IllegalArgumentException("El género con ID " + id + " no existe");
        }
        generoRepository.deleteById(id);
    }
}