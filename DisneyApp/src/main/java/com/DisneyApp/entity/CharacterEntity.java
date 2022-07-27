package com.DisneyApp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "characters")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;

    @ManyToMany(mappedBy = "moviesCharacters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovieEntity> charactersMovies = new ArrayList<>();

    public String toString(){
        return "Name: "+getName()+". " + "\n" +"Image: "+getImage();
    }
}
