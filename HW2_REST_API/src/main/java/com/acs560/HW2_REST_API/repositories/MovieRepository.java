package com.acs560.HW2_REST_API.repositories;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.acs560.HW2_REST_API.models.Movie;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.Builder;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@Builder
public class MovieRepository {

    private static final String FILE_NAME = "netflix_data.csv";
    private static List<Movie> movies;

    // Get the list of movies, initializing it if needed
    public static List<Movie> getMovies() {
        if (movies == null) {
            movies = initializeMovies();
        }
        return movies;
    }

    // Initialize movies from CSV file
    private static List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();
        try (FileReader fileReader = new FileReader(FILE_NAME);
             CSVReader reader = new CSVReaderBuilder(fileReader).withSkipLines(1).build()) {
            List<String[]> allData = reader.readAll();
            
            for (String[] row : allData) {
                if (row.length >= 4) { 
                    Movie movie = Movie.builder()
                        .title(row[2])        
                        .director(row[3])      
                        .type(row[1])          
                        .releaseYear(parseYear(row[7])) 
                        .build();
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // Helper method to parse the release year safely
    private static int parseYear(String yearStr) {
        try {
            return Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Find movies by title
    public List<Movie> findByTitle(String title) {
        return getMovies().stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    // Find movies by director
    public List<Movie> findByDirector(String director) {
        return getMovies().stream()
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director))
                .collect(Collectors.toList());
    }

    // Find movies by type
    public List<Movie> findByType(String type) {
        return getMovies().stream()
                .filter(movie -> movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    // Find movies by release year
    public List<Movie> findByReleaseYear(int releaseYear) {
        return getMovies().stream()
                .filter(movie -> movie.getReleaseYear() == releaseYear)
                .collect(Collectors.toList());
    }
    
 // Find movies by director & type
    public List<Movie> findByDirectorType(String director, String type) {
        return getMovies().stream()
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director) &&
                                 movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    
 // Find movies by release year & type
    public List<Movie> findByYearType(int releaseYear, String type) {
        return getMovies().stream()
        		.filter(movie -> movie.getReleaseYear() == releaseYear &&
                                 movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    
 // Find movies by director, release year & type
    public List<Movie> findByDirectorYearType(String director, int releaseYear, String type) {
        return getMovies().stream()
        		.filter(movie -> movie.getDirector().equalsIgnoreCase(director) &&
        		                 movie.getReleaseYear() == releaseYear &&
                                 movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}
