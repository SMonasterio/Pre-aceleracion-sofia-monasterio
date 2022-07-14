package com.DisneyApp.service;

import com.DisneyApp.entity.dto.CharacterDTO;
import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.repository.CharacterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    @Autowired
    private final CharacterRepository characterRepository;
    @Autowired
    private ObjectMapper mapper;


    @Autowired
    public CharacterService(CharacterRepository characterRepository, ObjectMapper mapper) {
        this.characterRepository = characterRepository;
        this.mapper = mapper;
    }

    public CharacterEntity save (CharacterEntity characterEntity){
        return characterRepository.save(characterEntity);
    }

    public List<CharacterEntity> allCharacters(){
        return characterRepository.findAll();
    }

    public CharacterEntity update(CharacterDTO characterDTO){
        CharacterEntity character = mapper.convertValue(characterDTO, CharacterEntity.class);
        return characterRepository.save(character);
    }

    public Optional<CharacterEntity> findCharcarterById(Integer id){
        return characterRepository.findById(id);
    }

    public void delete(Integer id) throws Exception {
        Optional<CharacterEntity> foundCharacter = findCharcarterById(id);
        if (foundCharacter.isPresent())
            characterRepository.deleteById(id);
        else
            throw new Exception("Character with id: "+id+" not found.");
    }




    public CharacterEntity findCharacterByName(String name){
        CharacterEntity foundCharacter = characterRepository.findCharacterByName(name);
        return foundCharacter ;
    }

    public CharacterEntity findCharacterByAge(Integer age){
        CharacterEntity foundCharacter = characterRepository.findCharacterByAge(age);
        return foundCharacter ;
    }

}