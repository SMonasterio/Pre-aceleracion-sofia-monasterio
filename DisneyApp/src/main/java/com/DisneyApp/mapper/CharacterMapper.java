package com.DisneyApp.mapper;

import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.dto.CharacterBasicDTO;
import com.DisneyApp.entity.dto.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired
    MovieMapper movieMapper;


    public CharacterEntity characterDTO2Entity(CharacterDTO characterDTO){
        CharacterEntity entity = new CharacterEntity();
        entity.setImage(characterDTO.getImage());
        entity.setName(characterDTO.getName());
        entity.setAge(characterDTO.getAge());
        entity.setWeight(characterDTO.getWeight());
        entity.setHistory(characterDTO.getHistory());
        return entity;
    }


    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean b){
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setImage(entity.getImage());
        characterDTO.setName(entity.getName());
        characterDTO.setAge(entity.getAge());
        characterDTO.setWeight(entity.getWeight());
        characterDTO.setHistory(entity.getHistory());
        if (b){
            characterDTO.setMovies(movieMapper.EntityList2DTOList(entity.getCharactersMovies(),false));
        }
        return characterDTO;
    }


    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> CharacterEntitiesList, boolean b){
        List<CharacterDTO> CharactersDTOList = new ArrayList<>();
        for (CharacterEntity entity: CharacterEntitiesList){
            CharactersDTOList.add(this.characterEntity2DTO(entity,b));
        }
        return CharactersDTOList;
    }

    public CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity){
        CharacterBasicDTO characterBasicDTO = new CharacterBasicDTO();
        characterBasicDTO.setImage(entity.getImage());
        characterBasicDTO.setName(entity.getName());
        return characterBasicDTO;
    }


    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities){
        List<CharacterBasicDTO> charactersBasicDTOList = new ArrayList<>();
        for (CharacterEntity entity: entities){
            charactersBasicDTOList.add(this.characterEntity2BasicDTO(entity));
        }
        return charactersBasicDTOList;
    }


}
