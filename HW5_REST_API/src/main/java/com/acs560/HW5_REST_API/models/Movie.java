package com.acs560.HW4_REST_API.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a movie with its attributes including title, director, type,
 * release year, and countries.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Movie {

    private String title;
    private String director;
    private String type;
    private String countries;
    private int releaseYear;
    

    @Override
    public String toString() {
        return String.join(",", title, director, type, countries, String.valueOf(releaseYear));
    }

}
