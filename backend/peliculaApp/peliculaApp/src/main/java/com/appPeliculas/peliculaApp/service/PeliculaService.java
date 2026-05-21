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

    public PeliculaService(PeliculaRepository peliculaRepository, GeneroRepository generoRepository) {
        this.peliculaRepository = peliculaRepository;
        this.generoRepository = generoRepository;
    }

    @Transactional(readOnly = true)
    public List<PeliculaDTO> obtenerTodas() {
        return peliculaRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PeliculaDTO obtenerPorId(Integer id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La película con ID " + id + " no existe"));
        return convertirADto(pelicula);
    }

    @Transactional
    public PeliculaDTO guardar(PeliculaDTO peliculaDTO) {
        // Usamos getGeneroId() porque ahora es una clase normal
        Genero genero = generoRepository.findById(peliculaDTO.getGeneroId())
                .orElseThrow(() -> new IllegalArgumentException("El género especificado no existe"));

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setAnyo(peliculaDTO.getAnio());
        pelicula.setDirector(peliculaDTO.getDirector());
        pelicula.setGenero(genero);

        Pelicula guardada = peliculaRepository.save(pelicula);
        return convertirADto(guardada);
    }

    @Transactional
    public PeliculaDTO actualizar(Integer id, PeliculaDTO peliculaDTO) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La película con ID " + id + " no existe"));

        Genero genero = generoRepository.findById(peliculaDTO.getGeneroId())
                .orElseThrow(() -> new IllegalArgumentException("El género especificado no existe"));

        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setAnyo(peliculaDTO.getAnio());
        pelicula.setDirector(peliculaDTO.getDirector());
        pelicula.setGenero(genero);

        Pelicula actualizada = peliculaRepository.save(pelicula);
        return convertirADto(actualizada);
    }

    @Transactional
    public void borrar(Integer id) {
        if (!peliculaRepository.existsById(id)) {
            throw new IllegalArgumentException("La película con ID " + id + " no existe");
        }
        peliculaRepository.deleteById(id);
    }

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