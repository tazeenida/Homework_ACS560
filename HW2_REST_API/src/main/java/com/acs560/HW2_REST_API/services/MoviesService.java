package com.acs560.HW2_REST_API.services;

import com.acs560.HW2_REST_API.models.Movie;
import com.acs560.HW2_REST_API.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * Retrieves all movies from the repository.
     * @return List of {@link Movie} objects.
     */
    public List<Movie> getAllMovies();
    
    /**
     * Retrieves movies by title.
     * @param title The title of the movies to search for.
     * @return List of {@link Movie} objects that match the specified title.
     */
    public List<Movie> getMoviesByTitle(String title);
       

    /**
     * Retrieves movies by director.
     * @param director The director of the movies to search for.
     * @return List of {@link Movie} objects directed by the specified director.
     */
    public List<Movie> getMoviesByDirector(String director);

    /**
     * Retrieves movies by type (e.g., Action, Comedy).
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified type.
     */
    public List<Movie> getMoviesByType(String type); 

    /**
     * Retrieves movies by release year.
     * @param releaseYear The release year of the movies to search for.
     * @return List of {@link Movie} objects released in the specified year.
     */
    public List<Movie> getMoviesByReleaseYear(int releaseYear);

    /**
     * Retrieves movies by director and type.
     * @param director The director of the movies to search for.
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified director and type.
     */
    public List<Movie> getMoviesByDirectorType(String director, String type); 

    /**
     * Retrieves movies by release year and type.
     * @param releaseYear The release year of the movies to search for.
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified release year and type.
     */
    public List<Movie> getMoviesByReleaseYearType(int releaseYear, String type); 
    /**
     * Retrieves movies by director, release year, and type.
     * @param director The director of the movies to search for.
     * @param releaseYear The release year of the movies to search for.
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified director, release year, and type.
     */
    public List<Movie> getMoviesByDirectorReleaseYearType(String director, int releaseYear, String type); 

     
}
