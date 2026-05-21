package com.appPeliculas.peliculaApp.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false)
    private Integer anyo;

    @Column(nullable = false, length = 100)
    private String director;

    // FetchType.LAZY optimiza el rendimiento al no traer el género de la BD a menos que se pida
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;

    public Pelicula() {
    }

    public Pelicula(Integer id, String titulo, Integer anyo, String director, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.anyo = anyo;
        this.director = director;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnyo() {
        return anyo;
    }

    public void setAnyo(Integer anyo) {
        this.anyo = anyo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}