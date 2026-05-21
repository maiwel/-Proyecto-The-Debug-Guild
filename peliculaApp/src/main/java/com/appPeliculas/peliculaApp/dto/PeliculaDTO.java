package com.appPeliculas.peliculaApp.dto;

public record PeliculaDTO(
    Integer id,
    String titulo,
    Integer anyo,
    String director,
    Integer generoId,      
    String generoNombre 
) {}