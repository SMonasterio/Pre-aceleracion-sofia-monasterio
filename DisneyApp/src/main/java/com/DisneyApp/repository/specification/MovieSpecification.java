package com.DisneyApp.repository.specification;

import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.entity.dto.MovieFiltersDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {
    public Specification<MovieEntity> getByFilters(MovieFiltersDTO movieFilters){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasLength(movieFilters.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFilters.getTitle().toLowerCase() + "%"
                        )
                );
            }

            if(!CollectionUtils.isEmpty(movieFilters.getGenre())) {
                Join<MovieEntity, GenreEntity> join = root.join("moviesGenres", JoinType.INNER);
                Expression<String> genresId = join.get("id");
                predicates.add(genresId.in(movieFilters.getGenre()));
            }

            query.distinct(true);

            String orderByField = "title";
            query.orderBy(
                    movieFilters.isAsc() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
