package com.DisneyApp.mapper;

import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.entity.dto.GenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {
    @Autowired
    MovieMapper movieMapper;

    //Method to make GenreEntity into GenreDTO
    public GenreDTO genreEntity2Dto(GenreEntity genreEntity,boolean b){
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setImage(genreEntity.getImage());
        genreDTO.setName(genreEntity.getName());
        if(b){
            genreDTO.setMovies(movieMapper.EntityList2DTOList(genreEntity.getGenresMovies(),false));
        }
        return genreDTO;

    }
    //Method to make GenreDTO into GenreEntity
    public GenreEntity genreDTO2Entity(GenreDTO genreDTO){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImage(genreDTO.getImage());
        genreEntity.setName(genreDTO.getName());
        return genreEntity;
    }

   //Method to make GenreEntityList into GenreDTOList
   public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> genreList, boolean b) {
        List<GenreDTO> genreDTOList = new ArrayList<>();
        for (GenreEntity entity : genreList) {
            genreDTOList.add(this.genreEntity2Dto(entity,false));
        }
        return genreDTOList;
    }

}
