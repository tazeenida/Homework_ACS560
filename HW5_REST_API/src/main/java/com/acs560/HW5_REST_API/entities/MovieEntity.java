package com.acs560.HW5_REST_API.entities;

import com.acs560.HW5_REST_API.models.Movie;

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
 */
@Entity
@Table(name = "Movies")
@Getter
@Setter // Allows updating fields
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String director;
    private String type;
    private String countries;
    @Column(name = "releaseyear")
    private Integer releaseYear;

    /**
     * Constructor to map from Movie model to MovieEntity.
     *
     * @param movie the Movie model instance
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
