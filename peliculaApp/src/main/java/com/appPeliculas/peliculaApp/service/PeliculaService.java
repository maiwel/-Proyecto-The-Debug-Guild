package com.appPeliculas.peliculaApp.service;

import com.appPeliculas.peliculaApp.domain.Genero;
import com.appPeliculas.peliculaApp.domain.Pelicula;
import com.appPeliculas.peliculaApp.dto.PeliculaDTO;
import com.appPeliculas.peliculaApp.repository.GeneroRepository;
import com.appPeliculas.peliculaApp.repository.PeliculaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final GeneroRepository generoRepository;

    // Inyección por constructor (Óptimo y limpio)
    public PeliculaService(PeliculaRepository peliculaRepository, GeneroRepository generoRepository) {
        this.peliculaRepository = peliculaRepository;
        this.generoRepository = generoRepository;
    }

    // 1. GET /api/peliculas -> Listar todas
    @Transactional(readOnly = true)
    public List<PeliculaDTO> obtenerTodas() {
        return peliculaRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // 2. GET /api/peliculas/{id} -> Buscar una por ID
    @Transactional(readOnly = true)
    public PeliculaDTO obtenerPorId(Integer id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La película con ID " + id + " no existe"));
        return convertirADto(pelicula);
    }

    // 3. POST /api/peliculas -> Crear una nueva película
    @Transactional
    public PeliculaDTO guardar(PeliculaDTO peliculaDTO) {
        Genero genero = generoRepository.findById(peliculaDTO.generoId())
                .orElseThrow(() -> new IllegalArgumentException("El género especificado no existe"));

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(peliculaDTO.titulo());
        pelicula.setAnyo(peliculaDTO.anyo());
        pelicula.setDirector(peliculaDTO.director());
        pelicula.setGenero(genero);

        Pelicula guardada = peliculaRepository.save(pelicula);
        return convertirADto(guardada);
    }

    // 4. PUT /api/peliculas/{id} -> Actualizar una película existente
    @Transactional
    public PeliculaDTO actualizar(Integer id, PeliculaDTO peliculaDTO) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La película con ID " + id + " no existe"));

        Genero genero = generoRepository.findById(peliculaDTO.generoId())
                .orElseThrow(() -> new IllegalArgumentException("El género especificado no existe"));

        pelicula.setTitulo(peliculaDTO.titulo());
        pelicula.setAnyo(peliculaDTO.anyo());
        pelicula.setDirector(peliculaDTO.director());
        pelicula.setGenero(genero);

        Pelicula actualizada = peliculaRepository.save(pelicula);
        return convertirADto(actualizada);
    }

    // 5. DELETE /api/peliculas/{id} -> Borrar una película
    @Transactional
    public void borrar(Integer id) {
        if (!peliculaRepository.existsById(id)) {
            throw new IllegalArgumentException("La película con ID " + id + " no existe");
        }
        peliculaRepository.deleteById(id);
    }

    // Método privado auxiliar para optimizar y reutilizar el mapeo a DTO plano
    private PeliculaDTO convertirADto(Pelicula pelicula) {
        return new PeliculaDTO(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getAnyo(),
                pelicula.getDirector(),
                pelicula.getGenero().getId(),
                pelicula.getGenero().getNombre()
        );
    }
}