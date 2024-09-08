package com.acs560.HW2_REST_API.controllers;

import com.acs560.HW2_REST_API.models.Movie;
import com.acs560.HW2_REST_API.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/moviesAnalyzer")
public class MovieAnalyzerController {

    @Autowired
    private MovieService movieService;
    
    @GetMapping("/countMoviesVsTVShows")
    public ResponseEntity<String> getCountMoviesVsTVShows() {
        String result = movieService.getCountMoviesVsTVShows();
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/avgMovies")
    public ResponseEntity<String> getAvgMovies() {
        String result = movieService.getAvgMovies();
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/countries")
    public ResponseEntity<String> getCountries() {
        String result = movieService.getCountries();
        return ResponseEntity.ok(result);
    }

}
