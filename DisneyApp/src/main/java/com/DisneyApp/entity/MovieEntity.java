package com.DisneyApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    private String title;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private Integer rating;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "movies_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<CharacterEntity> moviesCharacters = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> moviesGenres = new ArrayList<>();


    public void addCharacterToMovie(CharacterEntity charToBeAdded) {
        this.moviesCharacters.add(charToBeAdded);
    }
    public void removeCharacterToMovie(CharacterEntity charToRemove) {
        this.moviesCharacters.remove(charToRemove);
    }


    public void addGenreToMovie(GenreEntity genreToBeAdded) {
        this.moviesGenres.add(genreToBeAdded);
    }
}
