package com.DisneyApp.controller;

import com.DisneyApp.entity.dto.CharacterDTO;
import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.service.CharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<CharacterEntity> save (@RequestBody CharacterEntity characterEntity){
        CharacterEntity savedCharacter = characterService.save(characterEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCharacters(){
        return ResponseHandler.generateResponse("Character's list", HttpStatus.OK, characterService.allCharacters());
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody CharacterDTO characterDTO){
        ResponseEntity<Object> response = ResponseHandler.generateResponse("The character has been updated succesfully!",
                HttpStatus.OK, characterService.update(characterDTO));
        return response;
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<Object> searchCharacter (@PathVariable Integer id){
        ResponseEntity<Object> response=null;

        if (id != null && characterService.findCharcarterById(id).isPresent())
            response = ResponseHandler.generateResponse("Character found",
                    HttpStatus.OK, characterService.findCharcarterById(id));
        else
            response = ResponseHandler.generateResponse("Character not found",
                    HttpStatus.NOT_FOUND,null);
        return response;
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception {
        ResponseEntity<Object> response = null;

        if (characterService.findCharcarterById(id).isPresent()) {
            characterService.delete(id);
            response = ResponseHandler.generateResponse("Character deleted successfully", HttpStatus.OK, "id: "+id);
        }else {
            response = ResponseHandler.generateResponse("Character not found", HttpStatus.NOT_FOUND, "id: "+id);
        }
        return response;
    }

    @GetMapping()
    public ResponseEntity<Object> findCharacter (
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) Integer idMovie){
        CharacterEntity foundCharacterByName = characterService.findCharacterByName(name);
        String [] dataName = new String[2];
        dataName[0]=foundCharacterByName.getName();
        dataName[1]=foundCharacterByName.getImage();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dataName);
    }



}


