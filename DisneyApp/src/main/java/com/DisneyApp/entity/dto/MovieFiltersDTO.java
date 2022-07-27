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
public class MovieFiltersDTO {
    private String title;
    private List<Integer> genre;
    private String order;


    public boolean isAsc(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }
    public boolean isDesc(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
