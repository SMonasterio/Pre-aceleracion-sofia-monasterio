package com.DisneyApp.service;

import com.DisneyApp.entity.dto.CharacterDTO;

import java.util.List;

public interface ICharacterService {
    CharacterDTO save(CharacterDTO characterDTO);
    CharacterDTO findById(Integer id);
    List<CharacterDTO> getAll();
    void deleteByid(Integer id);
    CharacterDTO updateById(Integer id, CharacterDTO characterDTO);
    void addMovie(Integer id, Integer movieId);
}
