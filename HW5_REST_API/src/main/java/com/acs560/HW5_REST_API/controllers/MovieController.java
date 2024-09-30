package com.acs560.HW5_REST_API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acs560.HW5_REST_API.entities.MovieEntity;
import com.acs560.HW5_REST_API.models.Movie;
import com.acs560.HW5_REST_API.services.MoviesService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing movies.
 */
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MoviesService movieService;

    /**
     * Retrieves movies by ID.
     *
     * @param id The ID of the movie.
     * @return ResponseEntity containing the Movie or error status.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Movie> getMoviesById(@PathVariable int id) {
        List<MovieEntity> entities = movieService.getMoviesById(id);
        MovieEntity movie = entities.get(0);
        return ResponseEntity.ok(convertToModel(movie));
    }

    /**
     * Retrieves all movies.
     *
     * @return ResponseEntity containing the list of Movies.
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        List<MovieEntity> entities = movieService.getMovies();
        List<Movie> movies = entities.stream()
                                     .map(this::convertToModel)
                                     .collect(Collectors.toList());
        return ResponseEntity.ok(movies);
    }

    /**
     * Retrieves movies by title.
     *
     * @param title The title to search for.
     * @return ResponseEntity containing the list of Movies.
     */
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@PathVariable String title) {
        List<MovieEntity> entities = movieService.getMoviesByTitle(title);
        List<Movie> movies = entities.stream()
                                     .map(this::convertToModel)
                                     .collect(Collectors.toList());
        return ResponseEntity.ok(movies);
    }

    /**
     * Retrieves movies by director.
     *
     * @param director The director to search for.
     * @return ResponseEntity containing the list of Movies.
     */
    @GetMapping("/director/{director}")
    public ResponseEntity<List<Movie>> getMoviesByDirector(@PathVariable String director) {
        List<MovieEntity> entities = movieService.getMoviesByDirector(director);
        List<Movie> movies = entities.stream()
                                     .map(this::convertToModel)
                                     .collect(Collectors.toList());
        return ResponseEntity.ok(movies);
    }

    /**
     * Retrieves movies by type.
     *
     * @param type The type to search for.
     * @return ResponseEntity containing the list of Movies.
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Movie>> getMoviesByType(@PathVariable String type) {
        List<MovieEntity> entities = movieService.getMoviesByType(type);
        List<Movie> movies = entities.stream()
                                     .map(this::convertToModel)
                                     .collect(Collectors.toList());
        return ResponseEntity.ok(movies);
    }

    /**
     * Retrieves movies by release year.
     *
     * @param releaseYear The release year to search for.
     * @return ResponseEntity containing the list of Movies.
     */
    @GetMapping("/releaseYear/{releaseYear}")
    public ResponseEntity<List<Movie>> getMoviesByReleaseYear(@PathVariable int releaseYear) {
        List<MovieEntity> entities = movieService.getMoviesByReleaseYear(releaseYear);
        List<Movie> movies = entities.stream()
                                     .map(this::convertToModel)
                                     .collect(Collectors.toList());
        return ResponseEntity.ok(movies);
    }

    /**
     * Adds a new movie.
     *
     * @param movie The Movie model to add.
     * @return ResponseEntity with HTTP status.
     */
    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.add(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie added successfully.");
    }

    /**
     * Updates an existing movie.
     *
     * @param id    The ID of the movie to update.
     * @param movie The Movie model with updated details.
     * @return ResponseEntity with HTTP status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        movie.setId(id);
        movieService.update(id, movie);
        return ResponseEntity.ok("Movie updated successfully.");
    }

    /**
     * Deletes a movie by ID.
     *
     * @param id The ID of the movie to delete.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable int id) {
        movieService.delete(id);
        return ResponseEntity.ok("Movie deleted successfully.");
    }

    /**
     * Converts MovieEntity to Movie model.
     *
     * @param entity The MovieEntity.
     * @return The Movie model.
     */
    private Movie convertToModel(MovieEntity entity) {
        Movie movie = new Movie();
        movie.setId(entity.getId());
        movie.setTitle(entity.getTitle());
        movie.setDirector(entity.getDirector());
        movie.setType(entity.getType());
        movie.setCountries(entity.getCountries());
        movie.setReleaseYear(entity.getReleaseYear());
        return movie;
    }
}
