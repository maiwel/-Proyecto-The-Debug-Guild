package com.appPeliculas.peliculaApp.dto;

public class GeneroDTO {
    private Integer id;
    private String nombre;

    // Constructor
    public GeneroDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}