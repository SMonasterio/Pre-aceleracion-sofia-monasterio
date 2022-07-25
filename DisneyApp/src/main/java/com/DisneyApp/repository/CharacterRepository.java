package com.DisneyApp.repository;

import com.DisneyApp.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly=true)
public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer>, JpaSpecificationExecutor<CharacterEntity> {

    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);




}
