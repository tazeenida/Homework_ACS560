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
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieves all movies from the repository.
     * @return List of {@link Movie} objects.
     */
    public List<Movie> getAllMovies() {
        return movieRepository.getMovies();
    }

    /**
     * Retrieves movies by title.
     * @param title The title of the movies to search for.
     * @return List of {@link Movie} objects that match the specified title.
     */
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    /**
     * Retrieves movies by director.
     * @param director The director of the movies to search for.
     * @return List of {@link Movie} objects directed by the specified director.
     */
    public List<Movie> getMoviesByDirector(String director) {
        return movieRepository.findByDirector(director);
    }

    /**
     * Retrieves movies by type (e.g., Action, Comedy).
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified type.
     */
    public List<Movie> getMoviesByType(String type) {
        return movieRepository.findByType(type);
    }

    /**
     * Retrieves movies by release year.
     * @param releaseYear The release year of the movies to search for.
     * @return List of {@link Movie} objects released in the specified year.
     */
    public List<Movie> getMoviesByReleaseYear(int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    /**
     * Retrieves movies by director and type.
     * @param director The director of the movies to search for.
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified director and type.
     */
    public List<Movie> getMoviesByDirectorType(String director, String type) {
        return movieRepository.findByDirectorType(director, type);
    }

    /**
     * Retrieves movies by release year and type.
     * @param releaseYear The release year of the movies to search for.
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified release year and type.
     */
    public List<Movie> getMoviesByReleaseYearType(int releaseYear, String type) {
        return movieRepository.findByYearType(releaseYear, type);
    }

    /**
     * Retrieves movies by director, release year, and type.
     * @param director The director of the movies to search for.
     * @param releaseYear The release year of the movies to search for.
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified director, release year, and type.
     */
    public List<Movie> getMoviesByDirectorReleaseYearType(String director, int releaseYear, String type) {
        return movieRepository.findByDirectorYearType(director, releaseYear, type);
    }

    /**
     * Retrieves a summary of the count of movies versus TV shows.
     * @return A string summarizing the count of movies and TV shows.
     */
    public String getCountMoviesVsTVShows() {
        return movieRepository.getCountMoviesVsTVShows();
    }

    /**
     * Retrieves the average number of movies per year and the total number of movies.
     * @return A string summarizing the total number of movies and the average number of movies per year.
     */
    public String getAvgMovies() {
        return movieRepository.getAvgMovies();
    }

    /**
     * Retrieves unique country names and the count of movies produced in each country.
     * @return A string summarizing the unique countries and the count of movies for each country.
     */
    public String getCountries() {
        return movieRepository.getCountries();
    }
}
