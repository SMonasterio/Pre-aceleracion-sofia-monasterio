package com.DisneyApp.repository;

import com.DisneyApp.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer> {
}
