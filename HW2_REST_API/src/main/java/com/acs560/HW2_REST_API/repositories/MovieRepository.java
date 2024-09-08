package com.acs560.HW2_REST_API.repositories;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<Movie> getMovies() {
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
                        .countries(row[5])
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
    
    public String getCountMoviesVsTVShows() {
        List<Movie> allMovies = getMovies();
        int movieCount = (int) allMovies.stream().filter(movie -> movie.getType().equalsIgnoreCase("Movie")).count();
        int tvShowCount = (int) allMovies.stream().filter(movie -> movie.getType().equalsIgnoreCase("TV Show")).count();

        StringBuilder result = new StringBuilder("=== Count of Movies vs TV Shows ===\n");
        result.append(String.format("%-25s %s%n", "Type", "Count"));
        result.append("==============================\n");
        result.append(String.format("%-25s %d%n", "Movies", movieCount));
        result.append(String.format("%-25s %d%n", "TV Shows", tvShowCount));

        return result.toString();
    }
    
    public String getAvgMovies() {
        List<Movie> allMovies = getMovies();
        Map<Integer, Integer> moviesPerYear = new HashMap<>();
        int totalMovies = 0;

        for (Movie movie : allMovies) {
            if (movie.getType().equalsIgnoreCase("Movie")) {
                totalMovies++;
                moviesPerYear.put(movie.getReleaseYear(), moviesPerYear.getOrDefault(movie.getReleaseYear(), 0) + 1);
            }
        }

        StringBuilder textResult = new StringBuilder("=== Average Movies per Year ===\n");
        textResult.append(String.format("%-25s %s%n", "Total Movies:", "Average Movies per Year")); // Header
        textResult.append("==============================\n");

        if (moviesPerYear.size() > 0) {
            double average = (double) totalMovies / moviesPerYear.size();
            textResult.append(String.format("%-25d %.2f%n", totalMovies, average));
        } else {
            textResult.append(String.format("%-25s %s%n", "No movies found.", ""));
        }

        return textResult.toString();
    }
    
    public String getCountries() {
        List<Movie> allMovies = getMovies();
        Map<String, Integer> countryCounts = new HashMap<>();

        for (Movie movie : allMovies) {
            String countries = movie.getCountries();

            if (countries != null && !countries.trim().isEmpty()) {
                String[] countryList = countries.split(",");
                for (String country : countryList) {
                    country = country.trim();
                    countryCounts.put(country, countryCounts.getOrDefault(country, 0) + 1);
                }
            }
        }

        StringBuilder textResult = new StringBuilder("=== Unique Countries and their Counts ===\n");
        textResult.append(String.format("%-25s %s%n", "Country", "Count"));
        textResult.append("==============================\n");
        for (Map.Entry<String, Integer> entry : countryCounts.entrySet()) {
            textResult.append(String.format("%-25s %d%n", entry.getKey(), entry.getValue()));
        }

        return textResult.toString();
    }

}
