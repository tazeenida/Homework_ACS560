package com.acs560.HW5_REST_API.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the Movie model used in the API layer.
 */
@Getter
@Setter
@ToString
public class Movie {
    private int id;
    private String title;
    private String director;
    private String type;
    private String countries;
    private Integer releaseYear;
}
