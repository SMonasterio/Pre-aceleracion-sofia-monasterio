package com.DisneyApp.repository;

import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly=true)
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    List<MovieEntity> findAll(Specification<MovieEntity> spec);

}
