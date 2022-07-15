package com.DisneyApp.controller;

import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.entity.dto.GenreDTO;
import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.service.impl.GenreService;
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

    //FUNCIONA
    @PostMapping()
    public ResponseEntity<GenreDTO> saveGenre (@RequestBody GenreDTO genreDTO){
        GenreDTO savedGenre = genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    //FUNCIONA
   @GetMapping()
   public ResponseEntity<Object> getAllGenres(){
       return ResponseHandler.generateResponse(
               "Genre's list", HttpStatus.OK, genreService.getAll());
   }

   //FUNCIONA
   @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGenreById(@PathVariable Integer id){
        genreService.deleteByid(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Genre deleted sucsessfully");
   }

   //FUNCIONA
   @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Integer id, @RequestBody GenreDTO genreDTO){
        GenreDTO genre = genreService.updateById(id, genreDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genre);
   }

}
