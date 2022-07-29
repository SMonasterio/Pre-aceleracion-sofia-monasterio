package com.DisneyApp.controller;


import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.entity.dto.MovieBasicDTO;
import com.DisneyApp.entity.dto.MovieDTO;
import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.mapper.MovieMapper;
import com.DisneyApp.repository.CharacterRepository;
import com.DisneyApp.repository.GenreRepository;
import com.DisneyApp.repository.MovieRepository;
import com.DisneyApp.service.impl.CharacterService;
import com.DisneyApp.service.impl.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "Movies")
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @Autowired
    CharacterService characterService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    GenreRepository genreRepository;

    @ApiOperation(value="saveMovie", notes="Method to create a new movie")
    @PostMapping()
    public ResponseEntity<MovieDTO> save (@RequestBody MovieDTO movieDTO){
        MovieDTO savedMovie = movieService.save(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @ApiOperation(value="updateMovieById", notes="Method to update a movie selected by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Integer id , @RequestBody MovieDTO movieDTO){
        ResponseEntity<Object> response = null;
        MovieDTO found = movieService.findMovieDTOById(id);

        if (found!=null){
            MovieDTO movieUpdated = movieService.updateById(id, movieDTO);
            response=ResponseEntity.status(HttpStatus.ACCEPTED).body(movieUpdated);
        }else{
            response = ResponseHandler.generateResponse("Movie not found",
                    HttpStatus.NOT_FOUND, null);
        }
        return response;
    }
    @ApiOperation(value="searchMovieById", notes="Method to search a movie by it´s ID")
    @GetMapping("/searchById/{id}")
    public ResponseEntity<Object> searchMovie (@PathVariable Integer id){
        ResponseEntity<Object> response = null;
        MovieDTO movieFound = movieService.findMovieDTOById(id);
        List<MovieDTO> list = movieService.getAll();
        List<MovieBasicDTO> basicList = movieMapper.DTOList2BasicDTOList(list,true);

        if (movieFound == null) {
            response = ResponseHandler.generateResponse("Movie not found",
                    HttpStatus.NOT_FOUND, basicList);
        }else {
            response = ResponseHandler.generateResponse("Movie found",
                    HttpStatus.OK, movieFound);
        }
        return response;
    }

    @ApiOperation(value="deleteMovieById", notes="Method to delete a movie with it's ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception {
        ResponseEntity<Object> response = null;
        MovieDTO found = movieService.findMovieDTOById(id);
        if (found != null){
            movieService.deleteByid(id);
            response=ResponseHandler.generateResponse("Movie successfully deleted", HttpStatus.OK, "id: "+id);
        }else{
            response =ResponseHandler.generateResponse("Movie not found", HttpStatus.NOT_FOUND, "id: "+id);
        }

        return response;
    }

    @ApiOperation(value="getMovieByFilters", notes="Method to filter a movie by title or genre id")
    @GetMapping()
    public ResponseEntity<Object> getDetailsByFilters (
            @RequestParam (required = false) String title,
            @RequestParam (required = false) List<Integer> idGenre,
            @RequestParam (required = false, defaultValue = "ASC") String order) {

        List<MovieDTO> moviesFiltered = this.movieService.getMoviesByFilters(title, idGenre, order);
        List<MovieDTO> list = movieService.getAll();
        List<MovieBasicDTO> BasicList = movieMapper.DTOList2BasicDTOList(list,true);
        ResponseEntity response = null;

        if (moviesFiltered.isEmpty()){
            response = ResponseHandler.generateResponse("No movie match your request",
                    HttpStatus.NOT_FOUND,BasicList);
        }else{
            response = ResponseEntity.ok(moviesFiltered);
        }
        return response;
    }


    //ADD CHARACTER TO A MOVIE ENDPOINT
    @ApiOperation(value="addCharacterToMovie", notes="Method to add a character to a movie using theirs ids")
    @PostMapping("/{movieId}/character/{characterId}")
    public ResponseEntity<Object> addCharacterToMovie(@PathVariable Integer movieId, @PathVariable Integer characterId){
        Optional<MovieEntity> movie = movieRepository.findById(movieId);
        List<CharacterEntity> list = movie.get().getMoviesCharacters();
        Optional<CharacterEntity> character = characterRepository.findById(characterId);
        CharacterEntity entity = character.get();
        boolean present = list.contains(entity);
        ResponseEntity response = null;

        if (present){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This character is already associated to this movie");

        }else {
            movieService.addCharacter(movieId, characterId);
            response = ResponseEntity.status(HttpStatus.OK).body("Character successfully added to movie!");
        }
        return response;
    }

    //REMOVE CHARACTER FROM A MOVIE ENDPOINT
    @ApiOperation(value="removeCharacterToMovie", notes="Method to remove a character to a movie using theirs ids")
    @DeleteMapping("/{movieId}/character/{characterId}")
    public ResponseEntity<Object> removeCharacterToMovie(@PathVariable Integer movieId, @PathVariable Integer characterId){
        Optional<MovieEntity> movie = movieRepository.findById(movieId);
        List<CharacterEntity> list = movie.get().getMoviesCharacters();
        Optional<CharacterEntity> character = characterRepository.findById(characterId);
        CharacterEntity entity = character.get();
        boolean present = list.contains(entity);
        ResponseEntity response = null;

        if (present){
            movieService.removeCharacter(movieId, characterId);
            response = ResponseEntity.status(HttpStatus.ACCEPTED).body("Character successfully removed from movie!");
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This character is not associated to this movie ");
        }
        return response;
    }

    //ADD GENRE TO A MOVIE ENDPOINT
    @ApiOperation(value="addGenreToMovie", notes="Method to add a genre to a movie using theirs ids")
    @PostMapping("/{movieId}/genre/{genreId}")
    public ResponseEntity<Object> addGenreToMovie(@PathVariable Integer movieId, @PathVariable Integer genreId){
        Optional<MovieEntity> movie = movieRepository.findById(movieId);
        List<GenreEntity> list = movie.get().getMoviesGenres();
        Optional<GenreEntity> genre = genreRepository.findById(genreId);
        GenreEntity entity = genre.get();
        boolean present = list.contains(entity);
        ResponseEntity response = null;

        if(present){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This genre is already associated to this movie");
        }else{
            movieService.addGenre(movieId, genreId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Genre successfully added to movie!");
        }
        return response;


    }
}
