package com.DisneyApp.mapper;

import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.entity.dto.CharacterBasicDTO;
import com.DisneyApp.entity.dto.CharacterDTO;
import com.DisneyApp.entity.dto.MovieBasicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {
    @Autowired
    MovieMapper movieMapper;

    //----------------SINGLE ENTITIES MAPPERS------------------//

    //CharacterDTO to CharacterEntity
    public CharacterEntity characterDTO2Entity(CharacterDTO characterDTO){
        CharacterEntity entity = new CharacterEntity();
        entity.setImage(characterDTO.getImage());
        entity.setName(characterDTO.getName());
        entity.setAge(characterDTO.getAge());
        entity.setWeight(characterDTO.getWeight());
        entity.setHistory(characterDTO.getHistory());
        return entity;
    }

    //CharacterEntity to CharacterDTO
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
    //CharacterEntity TO CharacterBasicDTO
    public CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity){
        CharacterBasicDTO characterBasicDTO = new CharacterBasicDTO();
        characterBasicDTO.setImage(entity.getImage());
        characterBasicDTO.setName(entity.getName());
        return characterBasicDTO;
    }

    //----------------LIST'S MAPPERS-------------------//
    //CharacterDTO LIST to CharacterEntity LIST
    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> CharacterEntitiesList, boolean b){
        List<CharacterDTO> CharactersDTOList = new ArrayList<>();
        for (CharacterEntity entity: CharacterEntitiesList){
            CharactersDTOList.add(this.characterEntity2DTO(entity,b));
        }
        return CharactersDTOList;
    }

    //CharacterEntityLIST to CharacterBasicDTO LIST
    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities){
        List<CharacterBasicDTO> charactersBasicDTOList = new ArrayList<>();
        for (CharacterEntity entity: entities){
            charactersBasicDTOList.add(this.characterEntity2BasicDTO(entity));
        }
        return charactersBasicDTOList;
    }

    //CharacterDTO LIST to CharacterEntity LIST
    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> CharacterDTOList){
        List<CharacterEntity> CharactersEntityList = new ArrayList<>();
        for (CharacterDTO dto: CharacterDTOList){
            CharactersEntityList.add(this.characterDTO2Entity(dto));
        }
        return CharactersEntityList;
    }

}
