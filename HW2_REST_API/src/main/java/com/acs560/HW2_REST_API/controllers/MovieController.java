package com.acs560.HW2_REST_API.controllers;

import com.acs560.HW2_REST_API.models.Movie;
import com.acs560.HW2_REST_API.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	 * @return A list of movies including title, director, type, release year and country.
	 */
	@GetMapping
	public List<Movie> getAllMovies() {
		return movieService.getAllMovies();
	}

	/**
	 * Returns the movies filtered by title.
	 * 
	 * @param title The title of the movie.
	 * @return A list of movies with the specified title, including title, director, type, release year and country.
	 */
	@GetMapping("/title/{title}")
	public List<Movie> getMoviesByTitle(@PathVariable String title) {
		return movieService.getMoviesByTitle(title);
	}

	/**
	 * Returns the movies filtered by director
	 * 
	 * @param director The director of the movies.
	 * @return A list of movies directed by the specified director, including title, director, type, release year and country.
	 */
	@GetMapping("/director/{director}")
	public List<Movie> getMoviesByDirector(@PathVariable String director) {
		return movieService.getMoviesByDirector(director);
	}

	/**
	 * Returns the movies filtered by type
	 * 
	 * @param type The type of the movies (e.g., "Movie" or "TV Show").
	 * @return A list of movies with the specified type, including title, director, type, release year and country.
	 */
	@GetMapping("/type/{type}")
	public List<Movie> getMoviesByType(@PathVariable String type) {
		return movieService.getMoviesByType(type);
	}

	/**
	 * Returns the movies filtered by releaseYear
	 * 
	 * @param releaseYear The release year of the movies.
	 * @return A list of movies released in the specified year, including title, director, type, release year and country.
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
	 *         specified type, including title, director, type, release year and country.
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
	 *         specified type, including title, director, type, release year and country.
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
	 *         specified year, and with the specified type, including title, director, type, release year and country.
	 */
	@GetMapping("/director/{director}/releaseYear/{releaseYear}/type/{type}")
	public List<Movie> getMoviesByDirectorReleaseYearType(@PathVariable String director, @PathVariable int releaseYear,
			@PathVariable String type) {
		return movieService.getMoviesByDirectorReleaseYearType(director, releaseYear, type);
	}

}
