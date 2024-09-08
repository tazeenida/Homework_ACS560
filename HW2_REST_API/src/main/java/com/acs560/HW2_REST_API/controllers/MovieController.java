package com.acs560.HW2_REST_API.controllers;

import com.acs560.HW2_REST_API.models.Movie;
import com.acs560.HW2_REST_API.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/title/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable String title) {
        return movieService.getMoviesByTitle(title);
    }

    @GetMapping("/director/{director}")
    public List<Movie> getMoviesByDirector(@PathVariable String director) {
        return movieService.getMoviesByDirector(director);
    }

    @GetMapping("/type/{type}")
    public List<Movie> getMoviesByType(@PathVariable String type) {
        return movieService.getMoviesByType(type);
    }

    @GetMapping("/releaseYear/{releaseYear}")
    public List<Movie> getMoviesByReleaseYear(@PathVariable int releaseYear) {
        return movieService.getMoviesByReleaseYear(releaseYear);
    }
    
    @GetMapping("/director/{director}/type/{type}")
    public List<Movie> getMoviesByDirectorType(@PathVariable String director, @PathVariable String type) {
        return movieService.getMoviesByDirectorType(director, type);
    }
    
    @GetMapping("/releaseYear/{releaseYear}/type/{type}")
    public List<Movie> getMoviesByReleaseYearType(@PathVariable int releaseYear, @PathVariable String type) {
        return movieService.getMoviesByReleaseYearType(releaseYear, type);
    }
    
    @GetMapping("/director/{director}/releaseYear/{releaseYear}/type/{type}")
    public List<Movie> getMoviesByDirectorReleaseYearType(@PathVariable String director, @PathVariable int releaseYear, @PathVariable String type) {
        return movieService.getMoviesByDirectorReleaseYearType(director, releaseYear, type);
    }

}
