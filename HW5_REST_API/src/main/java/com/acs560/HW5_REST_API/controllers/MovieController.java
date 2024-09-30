package com.acs560.HW5_REST_API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acs560.HW5_REST_API.entities.MovieEntity;
import com.acs560.HW5_REST_API.exception.MoviesRepositoryManagementException;
import com.acs560.HW5_REST_API.models.Movie;
import com.acs560.HW5_REST_API.services.MoviesService;

import java.util.List;
import java.util.Optional;

/**
 * Movie Controller is the controller class for the movies API.
 */
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

	@Autowired
	private MoviesService movieService;
	/**
	 * Returns the movies filtered by id.
	 * 
	 * @param id The id of the movie.
	 * @return A list of movies with the specified id, title, including title, director,
	 *         type, release year and country.
	 */
	@GetMapping("/id/{id}")
	public List<MovieEntity> getMoviesById(@PathVariable int id) {
		return movieService.getMoviesById(id);
	}
	
	/**
	 * Retrieves the list of all movies
	 * 
	 * @return A list of movies including title, director, type, release year and
	 *         country.
	 */
	@GetMapping
	public List<MovieEntity> getMovies() {
		return movieService.getMovies();
	}

	/**
	 * Returns the movies filtered by title.
	 * 
	 * @param title The title of the movie.
	 * @return A list of movies with the specified title, including title, director,
	 *         type, release year and country.
	 */
	@GetMapping("/title/{title}")
	public List<MovieEntity> getMoviesByTitle(@PathVariable String title) {
		return movieService.getMoviesByTitle(title);
	}

	/**
	 * Returns the movies filtered by director
	 * 
	 * @param director The director of the movies.
	 * @return A list of movies directed by the specified director, including title,
	 *         director, type, release year and country.
	 */
	@GetMapping("/director/{director}")
	public List<MovieEntity> getMoviesByDirector(@PathVariable String director) {
		return movieService.getMoviesByDirector(director);
	}

	/**
	 * Returns the movies filtered by type
	 * 
	 * @param type The type of the movies (e.g., "Movie" or "TV Show").
	 * @return A list of movies with the specified type, including title, director,
	 *         type, release year and country.
	 */
	@GetMapping("/type/{type}")
	public List<MovieEntity> getMoviesByType(@PathVariable String type) {
		return movieService.getMoviesByType(type);
	}

	/**
	 * Returns the movies filtered by releaseYear
	 * 
	 * @param releaseYear The release year of the movies.
	 * @return A list of movies released in the specified year, including title,
	 *         director, type, release year and country.
	 */
	@GetMapping("/releaseYear/{releaseYear}")
	public List<MovieEntity> getMoviesByReleaseYear(@PathVariable int releaseYear) {
		return movieService.getMoviesByReleaseYear(releaseYear);
	}


//	/**
//	 * Adds a new movie to the repository.
//	 * 
//	 * This method processes a Movie object provided in the request body, adds it to
//	 * the movie repository, and responds with a 201 Created status.
//	 * 
//	 * @param movie The Movie object to be added. It should contain all required
//	 *              fields to be stored in the repository.
//	 * @return A {@link ResponseEntity} indicating the outcome of the request. The
//	 *         response has a 201 Created status if the movie was successfully
//	 *         added.
//	 */
//	@PostMapping
//	public ResponseEntity<String> add(@RequestBody Movie movie) {
//		try {
//			movieService.add(movie);
//			return ResponseEntity.status(HttpStatus.CREATED).body("Movie Added Successfully");
//		} catch (MoviesRepositoryManagementException e) {
//			throw e;
//		}
//	}
//
//	/**
//	 * Updates an existing movie in the repository.
//	 * 
//	 * This method processes a Movie object provided in the request body, updates
//	 * the corresponding movie in the repository, and responds with a 200 OK status.
//	 * 
//	 * @param movie The Movie object with updated details. The movie should have a
//	 *              unique identifier that allows it to be found and updated in the
//	 *              repository.
//	 * @return A {@link ResponseEntity} indicating the outcome of the request. The
//	 *         response has a 200 OK status if the movie was successfully updated.
//	 */
//	@PutMapping()
//	public ResponseEntity<Movie> update(@RequestBody Movie movie) {
//		movieService.update(movie);
//		return ResponseEntity.ok().build();
//	}
//
//	/**
//	 * Deletes a movie from the repository.
//	 * 
//	 * This method processes a Movie object provided in the request body, deletes
//	 * the corresponding movie from the repository, and responds with a 200 OK
//	 * status.
//	 * 
//	 * @param movie The Movie object to be deleted. The movie should have a unique
//	 *              identifier that allows it to be found and removed from the
//	 *              repository.
//	 * @return A {@link ResponseEntity} indicating the outcome of the request. The
//	 *         response has a 200 OK status if the movie was successfully deleted.
//	 */
//	@DeleteMapping()
//	public ResponseEntity<Movie> delete(@RequestBody Movie movie) {
//		movieService.delete(movie);
//		return ResponseEntity.ok().build();
//	}
}
