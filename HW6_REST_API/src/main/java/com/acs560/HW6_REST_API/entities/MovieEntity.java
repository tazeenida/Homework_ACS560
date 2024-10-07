package com.acs560.HW6_REST_API.entities;

import com.acs560.HW6_REST_API.models.Movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the Movie entity mapped to the "Movies" table in the database.
 * This entity contains information about a movie, including its title, director,
 * type, countries of production, and release year.
 */
@Entity
@Table(name = "Movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieEntity {

    /**
     * The unique identifier for the movie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The title of the movie.
     */
    private String title;

    /**
     * The director of the movie.
     */
    private String director;

    /**
     * The type or genre of the movie.
     */
    private String type;

    /**
     * The countries where the movie was produced.
     */
    private String countries;

    /**
     * The year the movie was released.
     */
    @Column(name = "releaseyear")
    private Integer releaseYear;

    /**
     * Constructor to map from Movie model to MovieEntity.
     *
     * @param movie the Movie model instance containing the details of the movie
     */
    public MovieEntity(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.type = movie.getType();
        this.countries = movie.getCountries();
        this.releaseYear = movie.getReleaseYear();
    }
}
