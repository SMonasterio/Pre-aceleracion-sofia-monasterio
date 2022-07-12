package com.DisneyApp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;

    @ManyToMany(mappedBy = "moviesGenres", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovieEntity> movies = new ArrayList<>();

}
