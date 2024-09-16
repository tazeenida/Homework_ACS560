package com.acs560.HW3_REST_API.controllers;

import com.acs560.HW3_REST_API.services.MoviesAnalyzerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * MovieAnalyzerController is the controller class for the movie analyzer API.
 */
@RestController
@RequestMapping("/api/v1/moviesAnalyzer")
public class MovieAnalyzerController {

    @Autowired
    private MoviesAnalyzerService moviesAnalyzerService;
    
    /**
     * Gets the count of movies versus TV shows.
     * @return A ResponseEntity containing the type and count of movies versus TV shows.
     */
    @GetMapping("/countMoviesVsTVShows")
    public ResponseEntity<String> getCountMoviesVsTVShows() {
        String result = moviesAnalyzerService.getCountMoviesVsTVShows();
        return ResponseEntity.ok(result);
    }
    
    
    /**
     * Gets the average number of movies per year.
     * @return A ResponseEntity containing the total number of movies and the average number of movies per year.
     */
    @GetMapping("/avgMovies")
    public ResponseEntity<String> getAvgMovies() {
        String result = moviesAnalyzerService.getAvgMovies();
        return ResponseEntity.ok(result);
    }
    
    /**
     * Gets the list of countries where the movies are available and their respective counts.
     * @return A ResponseEntity containing the list of countries and the count of movies in each country.
     */
    @GetMapping("/countries")
    public ResponseEntity<String> getCountries() {
        String result = moviesAnalyzerService.getCountries();
        return ResponseEntity.ok(result);
    }

}
