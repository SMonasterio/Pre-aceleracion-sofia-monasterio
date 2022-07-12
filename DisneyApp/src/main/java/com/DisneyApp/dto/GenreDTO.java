package com.DisneyApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    private Integer id;
    private String name;
    private String image;
    //private List<MovieDTO> genreMovies;
}
