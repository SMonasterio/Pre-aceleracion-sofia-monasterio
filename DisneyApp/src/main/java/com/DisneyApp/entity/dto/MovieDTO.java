package com.DisneyApp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private double rating;
    //private boolean deleted;
    private List<CharacterDTO> movieCharacter;
    private List<GenreDTO> movieGenres;
}
