package com.DisneyApp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterFiltersDTO {
    private String name;
    private Integer age;
    private Double weight;
    private List<Integer> movies;
    private String order;

    public boolean isAsc(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }
    public boolean isDesc(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
