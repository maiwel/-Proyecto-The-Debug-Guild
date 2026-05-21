package com.appPeliculas.peliculaApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private Integer anyo;
    private String director;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getAnyo() { return anyo; }
    public void setAnyo(Integer anyo) { this.anyo = anyo; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }
}