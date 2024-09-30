package com.acs560.HW4_REST_API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acs560.HW4_REST_API.exception.MoviesRepositoryManagementException;
import com.acs560.HW4_REST_API.models.Movie;
import com.acs560.HW4_REST_API.services.MoviesService;

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
	 * Retrieves the list of all movies
	 * 
	 * @return A list of movies including title, director, type, release year and
	 *         country.
	 */
	@GetMapping
	public List<Movie> getAllMovies() {
		return movieService.getAllMovies();
	}

	/**
	 * Returns the movies filtered by title.
	 * 
	 * @param title The title of the movie.
	 * @return A list of movies with the specified title, including title, director,
	 *         type, release year and country.
	 */
	@GetMapping("/title/{title}")
	public List<Movie> getMoviesByTitle(@PathVariable String title) {
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
	public List<Movie> getMoviesByDirector(@PathVariable String director) {
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
	public List<Movie> getMoviesByType(@PathVariable String type) {
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
	public List<Movie> getMoviesByReleaseYear(@PathVariable int releaseYear) {
		return movieService.getMoviesByReleaseYear(releaseYear);
	}

	/**
	 * Returns the movies filtered by director and type
	 * 
	 * @param director The director of the movies.
	 * @param type     The type of the movies (e.g., "Movie" or "TV Show").
	 * @return A list of movies released in the specified director and with the
	 *         specified type, including title, director, type, release year and
	 *         country.
	 */
	@GetMapping("/director/{director}/type/{type}")
	public List<Movie> getMoviesByDirectorType(@PathVariable String director, @PathVariable String type) {
		return movieService.getMoviesByDirectorType(director, type);
	}

	/**
	 * Returns the movies filtered by releaseYear and type
	 * 
	 * @param releaseYear The release year of the movies.
	 * @param type        The type of the movies (e.g., "Movie" or "TV Show").
	 * @return A list of movies released in the specified year and with the
	 *         specified type, including title, director, type, release year and
	 *         country.
	 */
	@GetMapping("/releaseYear/{releaseYear}/type/{type}")
	public List<Movie> getMoviesByReleaseYearType(@PathVariable int releaseYear, @PathVariable String type) {
		return movieService.getMoviesByReleaseYearType(releaseYear, type);
	}

	/**
	 * Returns the movies filtered by director, releaseYear and type
	 * 
	 * @param director    The director of the movies.
	 * @param releaseYear The release year of the movies.
	 * @param type        The type of the movies (e.g., "Movie" or "TV Show").
	 * @return A list of movies directed by the specified director, released in the
	 *         specified year, and with the specified type, including title,
	 *         director, type, release year and country.
	 */
	@GetMapping("/director/{director}/releaseYear/{releaseYear}/type/{type}")
	public List<Movie> getMoviesByDirectorReleaseYearType(@PathVariable String director, @PathVariable int releaseYear,
			@PathVariable String type) {
		return movieService.getMoviesByDirectorReleaseYearType(director, releaseYear, type);
	}

	/**
	 * Adds a new movie to the repository.
	 * 
	 * This method processes a Movie object provided in the request body, adds it to
	 * the movie repository, and responds with a 201 Created status.
	 * 
	 * @param movie The Movie object to be added. It should contain all required
	 *              fields to be stored in the repository.
	 * @return A {@link ResponseEntity} indicating the outcome of the request. The
	 *         response has a 201 Created status if the movie was successfully
	 *         added.
	 */
	@PostMapping
	public ResponseEntity<String> add(@RequestBody Movie movie) {
		try {
			movieService.add(movie);
			return ResponseEntity.status(HttpStatus.CREATED).body("Movie Added Successfully");
		} catch (MoviesRepositoryManagementException e) {
			throw e;
		}
	}

	/**
	 * Updates an existing movie in the repository.
	 * 
	 * This method processes a Movie object provided in the request body, updates
	 * the corresponding movie in the repository, and responds with a 200 OK status.
	 * 
	 * @param movie The Movie object with updated details. The movie should have a
	 *              unique identifier that allows it to be found and updated in the
	 *              repository.
	 * @return A {@link ResponseEntity} indicating the outcome of the request. The
	 *         response has a 200 OK status if the movie was successfully updated.
	 */
	@PutMapping()
	public ResponseEntity<Movie> update(@RequestBody Movie movie) {
		movieService.update(movie);
		return ResponseEntity.ok().build();
	}

	/**
	 * Deletes a movie from the repository.
	 * 
	 * This method processes a Movie object provided in the request body, deletes
	 * the corresponding movie from the repository, and responds with a 200 OK
	 * status.
	 * 
	 * @param movie The Movie object to be deleted. The movie should have a unique
	 *              identifier that allows it to be found and removed from the
	 *              repository.
	 * @return A {@link ResponseEntity} indicating the outcome of the request. The
	 *         response has a 200 OK status if the movie was successfully deleted.
	 */
	@DeleteMapping()
	public ResponseEntity<Movie> delete(@RequestBody Movie movie) {
		movieService.delete(movie);
		return ResponseEntity.ok().build();
	}

	/**
	 * Returns the movies filtered by a specified range of release years.
	 * 
	 * @param startYear The starting year of the range.
	 * @param endYear   The ending year of the range.
	 * @return A ResponseEntity containing a list of movies or a 404 if none are
	 *         found.
	 */
	@GetMapping("/range/{startYear}-{endYear}")
	public ResponseEntity<List<Movie>> getMoviesByReleaseYearRange(@PathVariable int startYear,
			@PathVariable int endYear) {

		List<Movie> movies = movieService.getMoviesByReleaseYearRange(startYear, endYear);

		if (movies.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.ok(movies);
	}
}
