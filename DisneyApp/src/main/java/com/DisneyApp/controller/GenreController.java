package com.DisneyApp.controller;

import com.DisneyApp.dto.GenreDTO;
import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @PostMapping()
    public ResponseEntity<GenreEntity> save (@RequestBody GenreDTO genre){
        GenreEntity savedGenre = genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

   @GetMapping()
   public ResponseEntity<Object> getAllGenres(){
       return ResponseHandler.generateResponse("Genre's list", HttpStatus.OK, genreService.allGenres());
   }

}