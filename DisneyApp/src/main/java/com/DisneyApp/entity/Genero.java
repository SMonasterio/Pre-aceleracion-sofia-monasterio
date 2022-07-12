package com.DisneyApp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@ToString
@Getter
@Setter
@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private String imagen;

    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    private List<Pelicula> peliculas;

    public Genero() {
    }

    public Genero(String nombre, String imagen, List<Pelicula> peliculas) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.peliculas = peliculas;
    }
}
