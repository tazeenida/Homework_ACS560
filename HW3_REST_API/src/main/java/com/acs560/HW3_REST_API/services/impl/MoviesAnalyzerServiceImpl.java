package com.acs560.HW3_REST_API.services.impl;

import com.acs560.HW3_REST_API.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acs560.HW3_REST_API.services.MoviesAnalyzerService;

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
public class MoviesAnalyzerServiceImpl implements MoviesAnalyzerService {

    @Autowired
    private MovieRepository movieRepository;

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
