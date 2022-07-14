package com.DisneyApp.repository;

import com.DisneyApp.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer> {

    @Query(
            value = "select C.* from characters C where name = ?", nativeQuery = true
    )
    CharacterEntity findCharacterByName(String name);

    @Query(
            value = "select C.* from characters C where age = ?", nativeQuery = true
    )
    CharacterEntity findCharacterByAge(Integer age);



}
