package com.DisneyApp.service;

import com.DisneyApp.entity.dto.GenreDTO;
import com.DisneyApp.entity.dto.MovieBasicDTO;
import com.DisneyApp.entity.dto.MovieDTO;

import java.util.List;

public interface IMovieService {
    //CRUD
    MovieDTO save(MovieDTO movieDTO);
    MovieDTO findMovieDTOById(Integer id);
    MovieDTO updateById(Integer id, MovieDTO movieDTO);
    List<MovieDTO> getAll();
    void deleteByid(Integer id);


    List<MovieDTO> getMoviesByFilters(String name, List<Integer> genre, String order);
    void addCharacter(Integer movieId, Integer characterId);
    void removeCharacter(Integer movieId, Integer characterId);

    void addGenre(Integer movieId, Integer genreId);

}
