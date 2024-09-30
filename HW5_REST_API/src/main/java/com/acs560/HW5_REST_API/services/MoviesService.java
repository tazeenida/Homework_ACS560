package com.acs560.HW5_REST_API.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.HW5_REST_API.entities.MovieEntity;
import com.acs560.HW5_REST_API.models.Movie;
import com.acs560.HW5_REST_API.repositories.MovieRepository;

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
public interface MoviesService {
    
	/**
     * Retrieves movies from the repository by id.
     * @return List of Movie objects.
     */
    public List<MovieEntity> getMoviesById(int id);
    
    /**
     * Retrieves all movies from the repository.
     * @return List of Movie objects.
     */
    public List<MovieEntity> getMovies();
    
    /**
     * Retrieves movies by title.
     * @param title The title of the movies to search for.
     * @return List of Movie objects that match the specified title.
     */
    public List<MovieEntity> getMoviesByTitle(String title);
       

    /**
     * Retrieves movies by director.
     * @param director The director of the movies to search for.
     * @return List of Movie objects directed by the specified director.
     */
    public List<MovieEntity> getMoviesByDirector(String director);

    /**
     * Retrieves movies by type (e.g., Action, Comedy).
     * @param type The type of the movies to search for.
     * @return List of Movie objects that match the specified type.
     */
    public List<MovieEntity> getMoviesByType(String type); 

    /**
     * Retrieves movies by release year.
     * @param releaseYear The release year of the movies to search for.
     * @return List of Movie objects released in the specified year.
     */
    public List<MovieEntity> getMoviesByReleaseYear(int releaseYear);

	

//    /**
//     * Adds a new movie to the repository.
//     * 
//     * This method accepts a Movie object and adds it to the repository.
//     * The movie should include all required fields necessary for its storage.
//     * 
//     * @param movie The Movie object to be added. This object should
//     *              contain the movie's details including title, director, type,
//     *              countries, and release year.
//     * @throws MoviesRepositoryManagementException if there is an error adding the movie
//     *                                             (e.g., if the movie already exists).
//     */
//    void add(Movie movie);
//
//    /**
//     * Updates an existing movie in the repository.
//     * 
//     * This method accepts a Movie object with updated details and replaces
//     * the existing movie in the repository with this updated information. The movie
//     * should have a unique identifier to match the existing record for updating.
//     * 
//     * @param movie The Movie object with updated details. The object should
//     *              include the necessary fields to identify and update the existing
//     *              movie record in the repository.
//     * @throws MoviesRepositoryManagementException if there is an error updating the movie
//     *                                             (e.g., if the movie does not exist).
//     */
//    void update(Movie movie);
//
//    /**
//     * Deletes a movie from the repository.
//     * 
//     * This method accepts a Movie object and removes the corresponding
//     * movie record from the repository. The movie should have a unique identifier
//     * to locate and delete the correct record.
//     * 
//     * @param movie The Movie object to be deleted. The object should
//     *              include sufficient details to identify the movie record to be removed.
//     * @throws MoviesRepositoryManagementException if there is an error deleting the movie
//     *                                             (e.g., if the movie does not exist).
//     */
//    void delete(Movie movie);

}
