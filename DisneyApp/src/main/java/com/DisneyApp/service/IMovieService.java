package com.DisneyApp.service;

import com.DisneyApp.entity.dto.GenreDTO;
import com.DisneyApp.entity.dto.MovieDTO;

import java.util.List;

public interface IMovieService {
    MovieDTO save(MovieDTO dto);
    MovieDTO findById(Integer id);
    List<MovieDTO> getAll();
    void deleteByid(Integer id);
    MovieDTO updateById(Integer id, MovieDTO movieDTO);
    void addCharacter(Integer movieId, Integer characterId);
    void addGenre(Integer movieId, Integer genreId);


    // FILTERS
    //List<MovieDTO> getByFilters(String name, Set<Long> genre, String order);
}
