package com.DisneyApp.service;

import com.DisneyApp.entity.dto.GenreDTO;
import com.DisneyApp.exceptions.BadRequestException;

import java.util.List;

public interface IGenreService {
    GenreDTO save(GenreDTO genreDTO);
    List<GenreDTO> getAll();
    void deleteByid(Integer id);
    GenreDTO updateById(Integer id, GenreDTO genreDTO);
}
