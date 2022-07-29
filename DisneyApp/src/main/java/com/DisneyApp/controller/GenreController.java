package com.DisneyApp.controller;

import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.entity.dto.GenreDTO;
import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.service.impl.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Genres")
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @ApiOperation(value="saveGenre", notes="Method to create a new genre")
    @PostMapping()
    public ResponseEntity<GenreDTO> saveGenre (@RequestBody GenreDTO genreDTO){
        GenreDTO savedGenre = genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @ApiOperation(value="getAllGenres", notes="Method to list all the genres available")
    @GetMapping()
    public ResponseEntity<Object> getAllGenres(){
       return ResponseHandler.generateResponse(
               "Genre's list", HttpStatus.OK, genreService.getAll());
   }

    @ApiOperation(value="deleteGenreById", notes="Method to delete a genre with it's ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGenreById(@PathVariable Integer id){
       genreService.deleteByid(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Genre deleted sucsessfully");
   }

    @ApiOperation(value="updateGenreById", notes="Method to update a genre selected by ID")
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Integer id, @RequestBody GenreDTO genreDTO){
        GenreDTO genre = genreService.updateById(id, genreDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genre);
   }

}
