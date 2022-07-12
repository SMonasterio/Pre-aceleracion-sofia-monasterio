package com.DisneyApp.service;

import com.DisneyApp.dto.GenreDTO;
import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.repository.GenreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private ObjectMapper mapper;

    public GenreService(GenreRepository genreRepository, ObjectMapper mapper) {
        this.genreRepository = genreRepository;
        this.mapper = mapper;
    }

    public GenreEntity save (GenreDTO genreDTO){
        System.out.println("guardar genero");
        GenreEntity genre = mapper.convertValue(genreDTO, GenreEntity.class);

        return genreRepository.save(genre);
    }

}
