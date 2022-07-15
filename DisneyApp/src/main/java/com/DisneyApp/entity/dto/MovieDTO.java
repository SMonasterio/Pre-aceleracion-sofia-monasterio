package com.DisneyApp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private String image;
    private String title;
    private LocalDate relaseDate;
    private Integer rating;
    private List<CharacterDTO> characters;
    private List<GenreDTO> genres;
}
