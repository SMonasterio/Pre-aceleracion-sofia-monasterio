package com.DisneyApp.mapper;

import com.DisneyApp.entity.CharacterEntity;
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

//TODO REVISAR PORQUE RELEASEDATE POSTEA NULL

    //--------------------SINGLE ENTITIES MAPPERS-----------------------//

    //---------MovieDTO to MovieEntity----------------//
    public MovieEntity MovieDTO2Entity(MovieDTO movieDTO){
        MovieEntity entity = new MovieEntity();
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setRating(movieDTO.getRating());
        entity.setReleaseDate(movieDTO.getReleaseDate());
        List<CharacterEntity> characterEntities =
                this.characterMapper.characterDTOList2EntityList(movieDTO.getCharacters());
        entity.setMoviesCharacters(characterEntities);

        return entity;
    }

    //--------------------MovieEntity to MovieDTO-----------------//
    public MovieDTO  MovieEntity2DTO(MovieEntity entity, boolean b){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setImage(entity.getImage());
        movieDTO.setTitle(entity.getTitle());
        movieDTO.setRating(entity.getRating());
        movieDTO.setReleaseDate(entity.getReleaseDate());
        if(b){
            movieDTO.setCharacters(this.characterMapper.characterEntityList2DTOList(entity.getMoviesCharacters(), false));
            //movieDTO.setGenres(this.genreMapper.genreEntityList2DTOList(entity.getMoviesGenres(),false));
        }
        return movieDTO;
    }

    //MovieEntity to MovieBasicDTO
    public MovieBasicDTO MovieEntity2BasicDTO(MovieEntity entity){
        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setImage(entity.getImage());
        movieBasicDTO.setTitle(entity.getTitle());
         movieBasicDTO.setReleaseDate(entity.getReleaseDate());

        return movieBasicDTO;
    }

    //MovieDTO to MovieBasicDTO
    public MovieBasicDTO MovieDTO2BasicDTO(MovieDTO movieDTO){
        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setImage(movieDTO.getImage());
        movieBasicDTO.setTitle(movieDTO.getTitle());
        movieBasicDTO.setReleaseDate(movieDTO.getReleaseDate());

        return movieBasicDTO;
    }

    //-------------------------------------ENTITIES LIST'S MAPPERS----------------------------//

    //MovieEntity LIST to MovieDTO LIST
    public List<MovieDTO> EntityList2DTOList(List<MovieEntity> moviesList, boolean b){
        List<MovieDTO> moviesDTOList = new ArrayList<>();
        for (MovieEntity entity: moviesList){
            moviesDTOList.add(this.MovieEntity2DTO(entity,b));
        }
        return moviesDTOList;
    }

    //MovieDTO LIST to MovieEntity LIST
    public List<MovieEntity> DTOList2EntityList(List<MovieDTO> movieList, boolean b){
        List<MovieEntity> movieEntityList = new ArrayList<>();
        for (MovieDTO dto: movieList){
            movieEntityList.add(this.MovieDTO2Entity(dto));
        }
        return movieEntityList;
    }

    //MovieEntity LIST to MovieBasicDTO LIST
    public List<MovieBasicDTO> MovieEntityList2BasicDTOList(List<MovieEntity> entities){
        List<MovieBasicDTO> movieBasicDTOSList = new ArrayList<>();
        for (MovieEntity ent: entities){
            movieBasicDTOSList.add(this.MovieEntity2BasicDTO(ent));
        }
        return movieBasicDTOSList;
    }

    //MovieDTO LIST to MovieBasicDTO LIST
    public List<MovieBasicDTO> DTOList2BasicDTOList(List<MovieDTO> movieList, boolean b){
        List<MovieBasicDTO> movieBasicDTOList = new ArrayList<>();
        for (MovieDTO dto: movieList){
            movieBasicDTOList.add(this.MovieDTO2BasicDTO(dto));
        }
        return movieBasicDTOList;
    }
}
