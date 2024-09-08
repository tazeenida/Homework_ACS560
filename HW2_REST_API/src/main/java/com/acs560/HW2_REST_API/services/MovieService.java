package com.acs560.HW2_REST_API.services;

import com.acs560.HW2_REST_API.models.Movie;
import com.acs560.HW2_REST_API.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.getMovies();
    }

    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> getMoviesByDirector(String director) {
        return movieRepository.findByDirector(director);
    }

    public List<Movie> getMoviesByType(String type) {
        return movieRepository.findByType(type);
    }

    public List<Movie> getMoviesByReleaseYear(int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }
    
    public List<Movie> getMoviesByDirectorType(String director, String type) {
    	 return movieRepository.findByDirectorType(director, type);
    }
    
    public List<Movie> getMoviesByReleaseYearType(int releaseYear, String type) {
   	 return movieRepository.findByYearType(releaseYear, type);
   }
    
    
    
    public List<Movie> getMoviesByDirectorReleaseYearType(String Director, int releaseYear, String type) {
      	 return movieRepository.findByDirectorYearType(Director, releaseYear, type);
      }
}
