package com.DisneyApp.controller;

import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.dto.CharacterDTO;
import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.service.impl.CharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private final CharacterService characterService;
    private ObjectMapper mapper;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }


    //funciona
    @PostMapping()
    public ResponseEntity<CharacterDTO> save (@RequestBody CharacterDTO characterDTO){
        CharacterDTO savedCharacter = characterService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    //funciona
    @GetMapping("/all")
    public ResponseEntity<Object> getAllCharacters(){
        return ResponseHandler.generateResponse("Character's list", HttpStatus.OK, characterService.getAll());
    }

    //no funciona bad request
    //revisar
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@RequestParam Integer id , @RequestBody CharacterDTO characterDTO){
        CharacterDTO charUpdated = characterService.updateById(id, characterDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(charUpdated);

    }

    //funciona
    @GetMapping("/searchById/{id}")
    public ResponseEntity<Object> searchCharacter (@PathVariable Integer id){
        ResponseEntity<Object> response=null;

        if (id != null) {
            characterService.findById(id);
            response = ResponseHandler.generateResponse("Character found",
                    HttpStatus.OK, characterService.findById(id));
        }else {
            response = ResponseHandler.generateResponse("Character not found",
                    HttpStatus.NOT_FOUND, null);

        }
        return response;
    }


    //funciona
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception {
        ResponseEntity<Object> response = null;
        characterService.deleteById(id);
        response = ResponseHandler.generateResponse("Character deleted successfully", HttpStatus.OK, "id: "+id);

        return response;
    }


    //funciona
    @GetMapping()
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters (
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) Double weight,
            @RequestParam (required = false) List<Integer> idMovie,
            @RequestParam (required = false, defaultValue = "ASC") String order) {

        List<CharacterDTO> characters = this.characterService.getDetailsByFilter(name, age, weight, idMovie, order);

        return ResponseEntity.ok(characters);
    }



}


