package com.DisneyApp.service;

import com.DisneyApp.entity.dto.MovieDTO;
import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    public MovieService(MovieRepository movieRepository, ObjectMapper mapper) {
        this.movieRepository = movieRepository;
        this.mapper = mapper;
    }

    public MovieEntity save (MovieEntity movieEntity){
        return movieRepository.save(movieEntity);
    }

    public List<MovieEntity> allMovies(){
        return movieRepository.findAll();
    }

    public MovieEntity update(MovieDTO movieDTO){
        MovieEntity movie = mapper.convertValue(movieDTO, MovieEntity.class);
        return movieRepository.save(movie);
    }

    public Optional<MovieEntity> findMovieById(Integer id){
        return movieRepository.findById(id);
    }

    public void delete(Integer id) throws Exception {
        Optional<MovieEntity> foundCharacter = findMovieById(id);
        if (foundCharacter.isPresent())
            movieRepository.deleteById(id);
        else
            throw new Exception("Movie with id: "+id+" not found.");
    }
}
