package com.DisneyApp.service;

import com.DisneyApp.entity.dto.CharacterBasicDTO;
import com.DisneyApp.entity.dto.CharacterDTO;

import java.util.List;

public interface ICharacterService {
    //CRUD
    CharacterDTO save(CharacterDTO characterDTO);
    CharacterDTO updateById(Integer id, CharacterDTO characterDTO);
    CharacterDTO findCharacterById(Integer id);
    List<CharacterDTO> getAll();
    void deleteById(Integer id);


    //FILTERS
    List<CharacterBasicDTO> getDetailsByFilter(String name, Integer age, Double weight, List<Integer> movies, String order);
}
