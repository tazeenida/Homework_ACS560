package com.acs560.HW5_REST_API.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.HW5_REST_API.entities.MovieEntity;
import com.acs560.HW5_REST_API.exception.MoviesRepositoryManagementException;
import com.acs560.HW5_REST_API.models.Movie;
import com.acs560.HW5_REST_API.repositories.MovieRepository;
import com.acs560.HW5_REST_API.services.MoviesService;

import java.util.List;

/**
 * Service class for handling business logic related to movies.
 * <p>
 * This class provides methods to access and manipulate movie data using the {@link MovieRepository}.
 * It offers various methods to retrieve movies based on different criteria and to get statistical information
 * about movies and TV shows.
 * </p>
 */
@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private MovieRepository movieRepository;
    /**
     * Retrieves movies from the repository by id.
     * @return List of Movie objects.
     */
    @Override
    public List<MovieEntity> getMoviesById(int id) {
        return movieRepository.findById(id);
    }
    
    /**
     * Retrieves all movies from the repository.
     * @return List of Movie objects.
     */
    @Override
    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    /**
     * Retrieves movies by title.
     * @param title The title of the movies to search for.
     * @return List of Movie objects that match the specified title.
     */
    public List<MovieEntity> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    /**
     * Retrieves movies by director.
     * @param director The director of the movies to search for.
     * @return List of Movie objects directed by the specified director.
     */
    public List<MovieEntity> getMoviesByDirector(String director) {
        return movieRepository.findByDirector(director);
    }

    /**
     * Retrieves movies by type (e.g., Action, Comedy).
     * @param type The type of the movies to search for.
     * @return List of Movie objects that match the specified type.
     */
    public List<MovieEntity> getMoviesByType(String type) {
        return movieRepository.findByType(type);
    }

    /**
     * Retrieves movies by release year.
     * @param releaseYear The release year of the movies to search for.
     * @return List of Movie objects released in the specified year.
     */
    public List<MovieEntity> getMoviesByReleaseYear(int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }
//
//
//
//    /**
//     * Adds a new movie to the repository.
//     * 
//     * @param movie The movie to be added. It contains details such as title, director, type, countries, and release year.
//     */
//    @Override
//    public void add(Movie movie) {
//    	try {
//			movieRepository.addMovie(movie);
//		} catch (MoviesRepositoryManagementException e) {
//			e.printStackTrace();
//		}
//    }
//
//    /**
//     * Updates an existing movie in the repository.
//     * 
//     * @param movie The movie with updated details. It should include the title, director, type, countries, and release year.
//     */
//    @Override    
//	public void update(Movie movie) {
//		try {
//			movieRepository.updateMovie(movie);
//		} catch (MoviesRepositoryManagementException e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//    /**
//     * Deletes a movie from the repository.
//     * 
//     * @param movie The movie to be deleted. The movie should match an existing movie in the repository based on its title and director.
//     */
//	@Override
//	public void delete(Movie movie) {
//		try {
//			movieRepository.deleteMovie(movie);
//		} catch (MoviesRepositoryManagementException e) {
//			e.printStackTrace();
//		}
//		
//	} 
}
