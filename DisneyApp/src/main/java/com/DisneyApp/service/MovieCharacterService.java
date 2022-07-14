package com.DisneyApp.service;

import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.MovieCharacters;
import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.repository.CharacterRepository;
import com.DisneyApp.repository.MovieCharacterRepository;
import com.DisneyApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieCharacterService {
    @Autowired
    private final MovieCharacterRepository movieCharacterRepository;
    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final CharacterRepository characterRepository;

    @Autowired
    public MovieCharacterService(MovieCharacterRepository movieCharacterRepository, MovieRepository movieRepository, CharacterRepository characterRepository) {
        this.movieCharacterRepository = movieCharacterRepository;
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }

    public MovieCharacters add(MovieCharacters movChar){
        Optional<MovieEntity> movie = movieRepository.findById(movChar.getMovie().getId());
        movChar.setMovie(movie.get());
        Optional<CharacterEntity> character = characterRepository.findById(movChar.getCharacter().getId());
        movChar.setCharacter(character.get());

        return movieCharacterRepository.save(movChar);
    }

    public Optional<MovieCharacters> findById(Integer id){

        return movieCharacterRepository.findById(id);
    }

    public void delete (Integer id) throws Exception {
        Optional<MovieCharacters> movieChar = findById(id);
        if (movieChar.isPresent())
            movieCharacterRepository.deleteById(id);
        else
            throw new Exception("NOT FOUND");
    }

    public List<MovieCharacters> listAll(){
        List<MovieCharacters> movieCharactersList = movieCharacterRepository.findAll();

        return movieCharactersList;
    }

    public List<MovieCharacters> findByMovieId(Integer id){

        return movieCharacterRepository.findByMovieId(id);

    }

}
