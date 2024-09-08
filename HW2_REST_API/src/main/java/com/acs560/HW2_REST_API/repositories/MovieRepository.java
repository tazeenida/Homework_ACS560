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

/**
 * Repository class for managing movie data.
 * This class is responsible for reading movie data from a CSV file, storing it in memory, and providing various 
 * methods to retrieve and analyze movie information.
 */
@Repository
@Getter
@Builder
public class MovieRepository {

    private static final String FILE_NAME = "netflix_data.csv";
    private static List<Movie> movies;


    /**
     * Retrieves the list of movies, initializing the list if it is not already initialized.
     * The list of movies is populated from the CSV file specified by {@code FILE_NAME} if it is not already loaded.
     * @return List of movies objects with its attributes including title, director, type
     * and release year
     */
    public List<Movie> getMovies() {
        if (movies == null) {
            movies = initializeMovies();
        }
        return movies;
    }
 
    /**
     * Initializes the list of movies by reading data from the CSV file.
     * Each row in the CSV file is parsed to create {@link Movie} instances. The first row is skipped assuming it
     * contains header information.
     * @return List of movies with its attributes including title, director, type
     * and release year
     */
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


    /**
     * Parses a string to an integer for the release year, with error handling for invalid formats.
     * @param yearStr The string representing the year.
     * @return The parsed integer year, or 0 if the string is not a valid integer.
     */
    private static int parseYear(String yearStr) {
        try {
            return Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    /**
     * Find movies by title
     * @param title The title of the movies to search for.
     * @return List of {@link Movie} objects released in the specified title.
     */
    public List<Movie> findByTitle(String title) {
        return getMovies().stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    /**
     * Find movies by director
     * @param director The director of the movies to search for.
     * @return List of {@link Movie} objects released in the specified director.
     */
    public List<Movie> findByDirector(String director) {
        return getMovies().stream()
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director))
                .collect(Collectors.toList());
    }

    
    /**
     * Find movies by type
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects released in the specified type.
     */
    public List<Movie> findByType(String type) {
        return getMovies().stream()
                .filter(movie -> movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

     
    /**
     * Find movies by release year
     * @param releaseYear The release year of the movies to search for.
     * @return List of {@link Movie} objects released in the specified year.
     */
    public List<Movie> findByReleaseYear(int releaseYear) {
        return getMovies().stream()
                .filter(movie -> movie.getReleaseYear() == releaseYear)
                .collect(Collectors.toList());
    }
    

    /**
     * Find movies by director & type
     * @param director The director of the movies to search for.
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified director and type.
     */
    public List<Movie> findByDirectorType(String director, String type) {
        return getMovies().stream()
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director) &&
                                 movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    

    /**
     * Find movies by release year & type
     * @param releaseYear The release year of the movies to search for.
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified release year and type.
     */
    public List<Movie> findByYearType(int releaseYear, String type) {
        return getMovies().stream()
        		.filter(movie -> movie.getReleaseYear() == releaseYear &&
                                 movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    
  
    /**
     * Find movies by director, release year & type
     * @param director The director of the movies to search for.
     * @param releaseYear The release year of the movies to search for.
     * @param type The type of the movies to search for.
     * @return List of {@link Movie} objects that match the specified director, release year, and type.
     */
    public List<Movie> findByDirectorYearType(String director, int releaseYear, String type) {
        return getMovies().stream()
        		.filter(movie -> movie.getDirector().equalsIgnoreCase(director) &&
        		                 movie.getReleaseYear() == releaseYear &&
                                 movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    
    /**
     * Get count of total Movies and Tv Shows.
     * @return A string summarizing the count of movies and TV shows.
     */
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
    
    
    /**
     * Calculates the average number of movies per year and the total number of movies.
     * This method aggregates movie data by year and calculates the average number of movies per year based on
     * the total number of movies.
     * @return A string summarizing the total number of movies and the average number of movies per year.
     */
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
    
    /**
     * Get unique country names and the count of movies in each.
     * @return A string summarizing the unique countries and the count of movies for each country.
     */
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
