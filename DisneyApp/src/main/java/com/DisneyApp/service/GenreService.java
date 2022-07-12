package com.DisneyApp.service;

import com.DisneyApp.dto.GenreDTO;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    public GenreDTO save (GenreDTO genreDTO){
        System.out.println("guardar genero");
        return genreDTO;
    }

}
