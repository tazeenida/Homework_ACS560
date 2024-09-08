package com.acs560.HW2_REST_API.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Movie {

    private String title;
    private String director;
    private String type; 
    private int releaseYear;
    private String countries;

}
