package com.appPeliculas.peliculaApp.repository;

import com.appPeliculas.peliculaApp.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    List<Pelicula> findAllByOrderByTituloAsc();
}