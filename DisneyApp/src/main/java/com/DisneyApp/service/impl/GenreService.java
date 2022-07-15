package com.DisneyApp.service.impl;

import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.entity.dto.GenreDTO;
import com.DisneyApp.exceptions.BadRequestException;
import com.DisneyApp.mapper.GenreMapper;
import com.DisneyApp.repository.GenreRepository;
import com.DisneyApp.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements IGenreService {
    @Autowired
    private final GenreMapper genreMapper;
    @Autowired
    private final GenreRepository genreRepository;

    public GenreService(GenreMapper genreMapper, GenreRepository genreRepository) {
        this.genreMapper = genreMapper;
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreDTO save(GenreDTO genreDTO){
        GenreEntity genreEntity = genreMapper.genreDTO2Entity(genreDTO);
        GenreEntity saved = genreRepository.save(genreEntity);
        GenreDTO genreResult = genreMapper.genreEntity2Dto(saved, false);
        return genreResult;
        
    }

    @Override
    public List<GenreDTO> getAll() {
        List<GenreEntity> genreEntityList = genreRepository.findAll();
        List<GenreDTO> genreDTOList = genreMapper.genreEntityList2DTOList(genreEntityList, false);

        return genreDTOList;
    }

    @Override
    public void deleteByid(Integer id) {
        genreRepository.deleteById(id);
    }

    @Override
    public GenreDTO updateById(Integer id, GenreDTO genreDTO) {
        Optional<GenreEntity> genreFound = genreRepository.findById(id);
        GenreEntity genreToBeUpdate = genreFound.get();

        genreToBeUpdate.setImage(genreDTO.getImage());
        genreToBeUpdate.setName(genreDTO.getName());

        GenreEntity genreUpdated = genreRepository.save(genreToBeUpdate);
        GenreDTO result = genreMapper.genreEntity2Dto(genreUpdated, false);
        return result;
    }

}
