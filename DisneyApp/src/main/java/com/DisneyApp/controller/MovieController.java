package com.DisneyApp.controller;

import com.DisneyApp.entity.dto.MovieDTO;
import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final MovieService movieService;
    private ObjectMapper mapper;


    public MovieController(MovieService movieService, ObjectMapper mapper) {
        this.movieService = movieService;
        this.mapper = mapper;
    }

    @PostMapping()
    public ResponseEntity<MovieEntity> save (@RequestBody MovieEntity movieEntity){
        MovieEntity savedMovie = movieService.save(movieEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllMovies(){
        return ResponseHandler.generateResponse("Movie's list", HttpStatus.OK, movieService.allMovies());
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody MovieDTO movieDTO){
        ResponseEntity<Object> response = ResponseHandler.generateResponse("The movie has been updated succesfully!",
                HttpStatus.OK, movieService.update(movieDTO));
        return response;
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<Object> searchMovie (@PathVariable Integer id){
        ResponseEntity<Object> response=null;

        if (id != null && movieService.findMovieById(id).isPresent())
            response = ResponseHandler.generateResponse("Movie found",
                    HttpStatus.OK, movieService.findMovieById(id));
        else
            response = ResponseHandler.generateResponse("Character not found",
                    HttpStatus.NOT_FOUND,null);
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception {
        ResponseEntity<Object> response = null;

        if (movieService.findMovieById(id).isPresent()) {
            movieService.delete(id);
            response = ResponseHandler.generateResponse("Movie deleted successfully", HttpStatus.OK, "id: "+id);
        }else {
            response = ResponseHandler.generateResponse("Movie not found", HttpStatus.NOT_FOUND, "id: "+id);
        }
        return response;
    }
}
