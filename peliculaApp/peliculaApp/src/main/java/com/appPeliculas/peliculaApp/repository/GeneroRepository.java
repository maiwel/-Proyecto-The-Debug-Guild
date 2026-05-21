package com.appPeliculas.peliculaApp.repository;

import com.appPeliculas.peliculaApp.domain.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    
}