package com.appPeliculas.peliculaApp.dto;

public class PeliculaDTO {
    private Integer id;
    private String titulo;
    private Integer anio;
    private String director;
    private Integer generoId;
    private String nombreGenero; // <--- Falta este campo

    // Constructor completo (6 campos)
    public PeliculaDTO(Integer id, String titulo, Integer anio, String director, Integer generoId, String nombreGenero) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.director = director;
        this.generoId = generoId;
        this.nombreGenero = nombreGenero;
    }

    // Constructor reducido (5 campos, útil por si no tienes el nombre del género)
    public PeliculaDTO(Integer id, String titulo, Integer anio, String director, Integer generoId) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.director = director;
        this.generoId = generoId;
    }

    // Getters
    public Integer getId() { return id; }
    public String getTitulo() { return titulo; }
    public Integer getAnio() { return anio; }
    public String getDirector() { return director; }
    public Integer getGeneroId() { return generoId; }
    public String getNombreGenero() { return nombreGenero; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public void setDirector(String director) { this.director = director; }
    public void setGeneroId(Integer generoId) { this.generoId = generoId; }
    public void setNombreGenero(String nombreGenero) { this.nombreGenero = nombreGenero; }
}