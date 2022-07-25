package com.DisneyApp.repository.specification;

import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.entity.dto.CharacterFiltersDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO charFilters){

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(charFilters.getName())) {
                predicates.add(
                       criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + charFilters.getName().toLowerCase() + "%"
                        )
                );
            }

            if(charFilters.getAge() != null) {
                predicates.add(criteriaBuilder.equal(root.get("age"), charFilters.getAge()));
            }

            if(charFilters.getWeight() != null) {
                predicates.add(criteriaBuilder.equal(root.get("weight"), charFilters.getWeight()));
            }


            if(!CollectionUtils.isEmpty(charFilters.getMovies())) {
                Join<CharacterEntity, MovieEntity> join = root.join("characterMovies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add((Predicate) moviesId.in(charFilters.getMovies()));
            }
            query.distinct(true);


            String orderByField = "name";
            query.orderBy(charFilters.isAsc()?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
