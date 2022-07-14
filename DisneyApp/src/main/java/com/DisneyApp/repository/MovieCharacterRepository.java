package com.DisneyApp.repository;

import com.DisneyApp.entity.MovieCharacters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacters, Integer> {
    List<MovieCharacters> findByMovieId(Integer id);
}
