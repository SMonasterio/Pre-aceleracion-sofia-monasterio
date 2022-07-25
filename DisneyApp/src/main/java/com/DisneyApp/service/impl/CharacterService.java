package com.DisneyApp.service.impl;

import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.dto.CharacterDTO;

import com.DisneyApp.entity.dto.CharacterFiltersDTO;
import com.DisneyApp.mapper.CharacterMapper;
import com.DisneyApp.repository.CharacterRepository;
import com.DisneyApp.repository.MovieRepository;
import com.DisneyApp.repository.specification.CharacterSpecification;
import com.DisneyApp.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CharacterService implements ICharacterService {
    @Autowired
    private final CharacterRepository characterRepository;
    @Autowired
    private final CharacterSpecification characterSpecification;
    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final CharacterMapper characterMapper;


    public CharacterService(CharacterRepository characterRepository, CharacterSpecification characterSpecification, MovieRepository movieRepository, CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterSpecification = characterSpecification;
        this.movieRepository = movieRepository;
        this.characterMapper = characterMapper;
    }


    @Override
    public CharacterDTO save(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(characterDTO);
        CharacterEntity saved = characterRepository.save(characterEntity);
        CharacterDTO characterResult = characterMapper.characterEntity2DTO(saved, false);
        return characterResult;
    }

    @Override
    public CharacterDTO findById(Integer id) {
        Optional<CharacterEntity> characterFound = characterRepository.findById(id);
        CharacterEntity charFound = characterFound.get();
        CharacterDTO charFoundDto = characterMapper.characterEntity2DTO(charFound, true);
        return charFoundDto;
    }

    @Override
    public List<CharacterDTO> getAll() {
        List<CharacterEntity> characterEntityList = characterRepository.findAll();
        List<CharacterDTO> characterDTOList = characterMapper.characterEntityList2DTOList(characterEntityList, false);
        return characterDTOList;
    }

    @Override
    public void deleteById(Integer id) {
        characterRepository.deleteById(id);
    }

    @Override
    public CharacterDTO updateById(Integer id, CharacterDTO characterDTO) {
        Optional<CharacterEntity> charFound = characterRepository.findById(id);
        CharacterEntity charToUpdate = charFound.get();
        //CharacterDTO charToUpdate = characterMapper.characterEntity2DTO(characterEntityFound, true);

        charToUpdate.setName(characterDTO.getName());
        charToUpdate.setAge(characterDTO.getAge());
        charToUpdate.setImage(characterDTO.getImage());
        charToUpdate.setWeight(characterDTO.getWeight());
        charToUpdate.setHistory(characterDTO.getHistory());

        CharacterEntity charUpdated = characterRepository.save(charToUpdate);
        CharacterDTO result = characterMapper.characterEntity2DTO(charUpdated, false);

        //CharacterDTO charUpdated = characterMapper.characterEntity2DTO(charToUpdate, false);

        return result;
    }

    @Override
    public void addMovie(Integer id, Integer movieId) {

    }

    @Override
    public List<CharacterDTO> getDetailsByFilter(String name, Integer age, Double weight, List<Integer> movies, String order) {
        CharacterFiltersDTO characterFiltersDTO = new CharacterFiltersDTO(name, age, weight, movies, order);
        List<CharacterEntity> characters = characterRepository.findAll(this.characterSpecification.getByFilters(characterFiltersDTO));
        List<CharacterDTO> characterDTOList = characterMapper.characterEntityList2DTOList(characters, true);

        return characterDTOList;
    }
}