package com.DisneyApp.controller;

import com.DisneyApp.entity.dto.CharacterBasicDTO;
import com.DisneyApp.entity.dto.CharacterDTO;
import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.mapper.CharacterMapper;
import com.DisneyApp.service.impl.CharacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Characters")
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterMapper characterMapper;


    @ApiOperation(value="saveCharacter", notes="Method to create a new character")
    @PostMapping()
    public ResponseEntity<CharacterDTO> save (@RequestBody CharacterDTO characterDTO){
        CharacterDTO savedCharacter = characterService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @ApiOperation(value="getAllCharacters", notes="Method to list all the characters available")
    @GetMapping("/all")
    public ResponseEntity<Object> getAllCharacters(){
        return ResponseHandler.generateResponse("Character's list", HttpStatus.OK, characterService.getAll());
    }

    @ApiOperation(value="searchCharacterById", notes="Method to search a character by it´s ID")
    @GetMapping("/searchById/{id}")
    public ResponseEntity<Object> searchCharacter (@PathVariable Integer id){
        ResponseEntity<Object> response = null;
        CharacterDTO charFound = characterService.findCharacterById(id);
        List<CharacterDTO> list = characterService.getAll();
        List<CharacterBasicDTO> basicList = characterMapper.characterDTOList2BasicDTOList(list);
        if (charFound == null) {
            response = ResponseHandler.generateResponse("Character not found",
                    HttpStatus.NOT_FOUND, basicList );
        }else {
            response = ResponseHandler.generateResponse("Character found",
                    HttpStatus.OK, charFound);


        }
        return response;
    }

    @ApiOperation(value="updateCharacterById", notes="Method to update a character selected by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Integer id , @RequestBody CharacterDTO characterDTO){
        ResponseEntity<Object> response = null;
        CharacterDTO charFound = characterService.findCharacterById(id);
        if (charFound != null){
            CharacterDTO charUpdated = characterService.updateById(id, characterDTO);
            response = ResponseEntity.status(HttpStatus.ACCEPTED).body(charUpdated);
        }else{
            response = ResponseHandler.generateResponse("Character not found",
                    HttpStatus.NOT_FOUND, null);
        }
        return response;
    }

    @ApiOperation(value="deleteCharacterById", notes="Method to delete a character with it's ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception {
        ResponseEntity<Object> response = null;
        CharacterDTO found = characterService.findCharacterById(id);
        if (found != null) {
            characterService.deleteById(id);
            response = ResponseHandler.generateResponse("Character deleted successfully", HttpStatus.OK, "id: " + id);
        }else{
            response = ResponseHandler.generateResponse("Character not found", HttpStatus.NOT_FOUND, "id: " + id);

        }
        return response;
    }

    @ApiOperation(value="getCharacterByFilters", notes="Method to filter a character by name, age, weight or movie id")
    @GetMapping()
    public ResponseEntity<Object> getDetailsByFilters (
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) Double weight,
            @RequestParam (required = false) List<Integer> idMovie,
            @RequestParam (required = false, defaultValue = "ASC") String order) {

        List<CharacterBasicDTO> characters = this.characterService.getDetailsByFilter(name, age, weight, idMovie, order);
        List<CharacterDTO> list = characterService.getAll();
        List<CharacterBasicDTO> basicList= characterMapper.characterDTOList2BasicDTOList(list);
        ResponseEntity response = null;
        if (characters.isEmpty()){
            response = ResponseHandler.generateResponse("Character not found",
                    HttpStatus.NOT_FOUND, basicList );
        }else{
            response = ResponseEntity.ok(characters);
        }
        return response;
    }



}


