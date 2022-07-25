package com.DisneyApp.service;

import com.DisneyApp.entity.dto.CharacterDTO;

import java.util.List;

public interface ICharacterService {
    CharacterDTO save(CharacterDTO characterDTO);
    CharacterDTO findById(Integer id);
    List<CharacterDTO> getAll();
    void deleteById(Integer id);
    CharacterDTO updateById(Integer id, CharacterDTO characterDTO);
    void addMovie(Integer id, Integer movieId);
    List<CharacterDTO> getDetailsByFilter(String name, Integer age, Double weight, List<Integer> movies, String order);
}
