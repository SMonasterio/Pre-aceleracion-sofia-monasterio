package com.DisneyApp.service.impl;

import com.DisneyApp.entity.dto.CharacterDTO;

import com.DisneyApp.mapper.CharacterMapper;
import com.DisneyApp.repository.CharacterRepository;
import com.DisneyApp.repository.MovieRepository;
import com.DisneyApp.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CharacterService implements ICharacterService {
    @Autowired
    private final CharacterRepository characterRepository;
    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final CharacterMapper characterMapper;

    public CharacterService(CharacterRepository characterRepository, MovieRepository movieRepository, CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
        this.characterMapper = characterMapper;
    }


    @Override
    public CharacterDTO save(CharacterDTO characterDTO) {
        return null;
    }

    @Override
    public CharacterDTO findById(Integer id) {
        return null;
    }

    @Override
    public List<CharacterDTO> getAll() {
        return null;
    }

    @Override
    public void deleteByid(Integer id) {

    }

    @Override
    public CharacterDTO updateById(Integer id, CharacterDTO characterDTO) {
        return null;
    }

    @Override
    public void addMovie(Integer id, Integer movieId) {

    }
}