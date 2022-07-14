package com.DisneyApp.controller;

import com.DisneyApp.entity.MovieCharacters;
import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.repository.MovieCharacterRepository;
import com.DisneyApp.service.MovieCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movieCharacter")
public class MovieCharacterController {
    @Autowired
    MovieCharacterService movieCharacterService;


    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody MovieCharacters movieCharacters){
        return ResponseHandler.generateResponse(
                "La relación producto y característica se ha agregado correctamente",
                HttpStatus.OK,
                movieCharacterService.add(movieCharacters));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> buscarTodasProdCaract(){
        return ResponseHandler.generateResponse(
                "Listado de todas las relaciones productos y características",
                HttpStatus.OK, movieCharacterService.listAll());
    }
}
