package com.appPeliculas.peliculaApp.repository;

import com.appPeliculas.peliculaApp.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}