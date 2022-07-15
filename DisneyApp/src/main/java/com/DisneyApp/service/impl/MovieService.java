package com.DisneyApp.service.impl;

import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.entity.dto.MovieDTO;
import com.DisneyApp.mapper.MovieMapper;
import com.DisneyApp.repository.CharacterRepository;
import com.DisneyApp.repository.GenreRepository;
import com.DisneyApp.repository.MovieRepository;
import com.DisneyApp.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService implements IMovieService {

    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final GenreRepository genreRepository;
    @Autowired
    private final CharacterRepository characterRepository;
    @Autowired
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, CharacterRepository characterRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.characterRepository = characterRepository;
        this.movieMapper = movieMapper;
    }


    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        MovieEntity movieEntity = movieMapper.MovieDTO2Entity(movieDTO);
        MovieEntity saved = movieRepository.save(movieEntity);
        MovieDTO movieResult = movieMapper.MovieEntity2DTO(saved, false);

        return movieResult;
    }

    //Method to find a movie by id and show details
    @Override
    public MovieDTO findById(Integer id) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        MovieDTO movieDTO = movieMapper.MovieEntity2DTO(movieEntity.get(), true);

        return movieDTO;
    }

    @Override
    public List<MovieDTO> getAll() {
        List<MovieEntity> movies = movieRepository.findAll();
        List<MovieDTO> moviesDto = movieMapper.EntityList2DTOList(movies, false);

        return moviesDto;
    }

    @Override
    public void deleteByid(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO updateById(Integer id, MovieDTO movieDTO) {
        MovieDTO movieToBeUpdate = this.findById(id);
        movieToBeUpdate.setImage(movieDTO.getImage());
        movieToBeUpdate.setTitle(movieDTO.getTitle());
        movieToBeUpdate.setRelaseDate(movieDTO.getRelaseDate());
        movieToBeUpdate.setRating(movieDTO.getRating());

        MovieEntity movieUpdated = movieMapper.MovieDTO2Entity(movieToBeUpdate);
        MovieEntity updated = movieRepository.save(movieUpdated);
        MovieDTO result = movieMapper.MovieEntity2DTO(updated, false);
        return result;
    }

    @Override
    public void addCharacter(Integer movieId, Integer characterId) {
        Optional<MovieEntity> movie = movieRepository.findById(movieId);
        Optional<CharacterEntity> character = characterRepository.findById(characterId);
        MovieEntity movieFound = movie.get();
        movieFound.setMoviesCharacters((List<CharacterEntity>) character.get());
        movieRepository.save(movieFound);
    }

    @Override
    public void addGenre(Integer movieId, Integer genreId) {
        MovieDTO savedMovie = this.findById(movieId);
        Optional<GenreEntity> savedGenre = genreRepository.findById(genreId);
        MovieEntity movie = movieMapper.MovieDTO2Entity(savedMovie);
        movie.getMoviesGenres().size();
        movie.setMoviesGenres((List<GenreEntity>) savedGenre.get());
        movieRepository.save(movie);
    }

    //filters
    /*@Override
    public List<MovieDTO> getByFilters(String title, Set<Long> genre, String order) {
        MovieFiltersDTO movieFilters = new MovieFiltersDTO(title, genre, order);
        List<MovieEntity> entityList = movieRepository.findAll( movieSpecs.getFiltered(movieFilters));
        List<MovieDTO> resultDTO = movieMapper.EntityList2DTOList(entityList, true);
        return resultDTO;
    }*/

}
