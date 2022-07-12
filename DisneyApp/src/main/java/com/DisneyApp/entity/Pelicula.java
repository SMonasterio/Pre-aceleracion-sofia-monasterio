package com.DisneyApp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ToString
@Getter
@Setter
@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String imagen;
    private String titulo;
    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private Date fechaDeCreacion;
    private Integer calificacion;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name = "pelicula_personaje",
                joinColumns = @JoinColumn(name = "pelicula_id"),
                inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<Personaje> personajes = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "peliculas_id")
    private Genero genero;




    public Pelicula() {
    }
    public Pelicula(String imagen, String titulo, Date fechaDeCreacion, Integer calificacion, List<Personaje> personajes) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.calificacion = calificacion;
        this.personajes = personajes;
    }
}
