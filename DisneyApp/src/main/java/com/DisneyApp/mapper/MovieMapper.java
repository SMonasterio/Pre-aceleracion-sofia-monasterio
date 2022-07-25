package com.DisneyApp.mapper;

import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.entity.dto.MovieBasicDTO;
import com.DisneyApp.entity.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {
    @Autowired
    CharacterMapper characterMapper;
    @Autowired
    GenreMapper genreMapper;


    public MovieEntity MovieDTO2Entity(MovieDTO movieDTO){
        MovieEntity entity = new MovieEntity();
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setRating(movieDTO.getRating());
        entity.setReleaseDate(movieDTO.getRelaseDate());
        return entity;
    }


    public MovieDTO  MovieEntity2DTO(MovieEntity entity, boolean b){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setImage(entity.getImage());
        movieDTO.setTitle(entity.getTitle());
        movieDTO.setRating(entity.getRating());
        movieDTO.setRelaseDate(entity.getReleaseDate());
        if(b){
            movieDTO.setCharacters(this.characterMapper.characterEntityList2DTOList(entity.getMoviesCharacters(),false));
            movieDTO.setGenres(this.genreMapper.genreEntityList2DTOList(entity.getMoviesGenres(),false));
        }
        return movieDTO;
    }


    public List<MovieDTO> EntityList2DTOList(List<MovieEntity> movieList, boolean b){
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (MovieEntity entity: movieList){
            movieDTOList.add(this.MovieEntity2DTO(entity,b));
        }
        return movieDTOList;
    }



    public MovieBasicDTO MovieEntity2BasicDTO(MovieEntity entity){
        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setImage(entity.getImage());
        movieBasicDTO.setTitle(entity.getTitle());
        movieBasicDTO.setRelaseDate(entity.getReleaseDate());
        return movieBasicDTO;
    }


    public List<MovieBasicDTO> MovieEntityList2BasicDTOList(List<MovieEntity> entities){
        List<MovieBasicDTO> movieBasicDTOSList = new ArrayList<>();
        for (MovieEntity ent: entities){
            movieBasicDTOSList.add(this.MovieEntity2BasicDTO(ent));
        }
        return movieBasicDTOSList;
    }




}
