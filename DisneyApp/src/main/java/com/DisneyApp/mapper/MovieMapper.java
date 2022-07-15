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

    //Method to make movieDTO into movieEntity
    public MovieEntity MovieDTO2Entity(MovieDTO movieDTO){
        MovieEntity entity = new MovieEntity();
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setRating(movieDTO.getRating());
        entity.setReleaseDate(movieDTO.getRelaseDate());
        return entity;
    }

    //Method to make movieEntity into movieDTO
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

    //Method to make movieEntityLIST into movieDTOList
    public List<MovieDTO> EntityList2DTOList(List<MovieEntity> movieList, boolean b){
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (MovieEntity entity: movieList){
            movieDTOList.add(this.MovieEntity2DTO(entity,b));
        }
        return movieDTOList;
    }


    //Method to make movieEntity into movieBasicDTO
    public MovieBasicDTO MovieEntity2BasicDTO(MovieEntity entity){
        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setImage(entity.getImage());
        movieBasicDTO.setTitle(entity.getTitle());
        movieBasicDTO.setRelaseDate(entity.getReleaseDate());
        return movieBasicDTO;
    }

    //Method to make MovieEntityList into MovieBasicDTOList
    public List<MovieBasicDTO> MovieEntityList2BasicDTOList(List<MovieEntity> entities){
        List<MovieBasicDTO> movieBasicDTOSList = new ArrayList<>();
        for (MovieEntity ent: entities){
            movieBasicDTOSList.add(this.MovieEntity2BasicDTO(ent));
        }
        return movieBasicDTOSList;
    }

    //Utils
    /*public LocalDate String2LocalDate(String enteredDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/yyyy");
        LocalDate transformedDate = LocalDate.parse(enteredDate, formatter);
        return transformedDate;
    }*/

    /*public String LocalDateToString(LocalDate creationDate) {
        String formattedDate = creationDate.format(DateTimeFormatter.ofPattern("DD/MM/yyyy"));
        return formattedDate;
    }*/


}
