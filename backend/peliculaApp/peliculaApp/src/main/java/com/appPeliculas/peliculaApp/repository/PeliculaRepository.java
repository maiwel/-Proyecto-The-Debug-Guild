package com.appPeliculas.peliculaApp.repository;

import com.appPeliculas.peliculaApp.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    List<Pelicula> findByGeneroId(Integer generoId);
}