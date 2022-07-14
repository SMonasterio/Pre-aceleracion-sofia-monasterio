package com.DisneyApp.repository;

import com.DisneyApp.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
}
