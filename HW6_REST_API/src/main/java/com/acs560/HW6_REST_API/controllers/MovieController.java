package com.acs560.HW6_REST_API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acs560.HW6_REST_API.entities.MovieEntity;
import com.acs560.HW6_REST_API.models.Movie;
import com.acs560.HW6_REST_API.services.MoviesService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing movies.
 * This controller provides endpoints for CRUD operations on movies, including
 * retrieval, creation, updating, and deletion of movies in the system.
 */
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MoviesService movieService;

    /**
     * Retrieves a movie by its unique ID.
     * 
     * @param id The unique ID of the movie to retrieve.
     * @return ResponseEntity containing the movie if found, or an error status if not.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Movie> getMoviesById(@PathVariable int id) {
        List<MovieEntity> entities = movieService.getMoviesById(id);
        MovieEntity movie = entities.get(0);  // Assuming only one movie is retrieved by ID.
        return ResponseEntity.ok(convertToModel(movie));
    }

    /**
     * Retrieves all movies from the system.
     * 
     * @return ResponseEntity containing the list of all movies.
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
     * Retrieves movies by their title.
     * 
     * @param title The title of the movies to search for.
     * @return ResponseEntity containing the list of movies with the given title.
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
     * Retrieves movies by the director's name.
     * 
     * @param director The director of the movies to search for.
     * @return ResponseEntity containing the list of movies directed by the given director.
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
     * Retrieves movies by their type (genre).
     * 
     * @param type The type or genre of the movies to search for.
     * @return ResponseEntity containing the list of movies with the given type.
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
     * Retrieves movies by their release year.
     * 
     * @param releaseYear The release year of the movies to search for.
     * @return ResponseEntity containing the list of movies released in the given year.
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
     * Adds a new movie to the system.
     * 
     * @param movie The movie details to add.
     * @return ResponseEntity with HTTP status indicating the result of the operation.
     */
    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.add(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie added successfully.");
    }

    /**
     * Updates an existing movie in the system.
     * 
     * @param id    The unique ID of the movie to update.
     * @param movie The movie details to update.
     * @return ResponseEntity with HTTP status indicating the result of the update.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        movie.setId(id);
        movieService.update(id, movie);
        return ResponseEntity.ok("Movie updated successfully.");
    }

    /**
     * Deletes a movie from the system by its ID.
     * 
     * @param id The unique ID of the movie to delete.
     * @return ResponseEntity with HTTP status indicating the result of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable int id) {
        movieService.delete(id);
        return ResponseEntity.ok("Movie deleted successfully.");
    }

    /**
     * Converts a MovieEntity object to a Movie model.
     * 
     * @param entity The MovieEntity to convert.
     * @return The Movie model with corresponding fields.
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
