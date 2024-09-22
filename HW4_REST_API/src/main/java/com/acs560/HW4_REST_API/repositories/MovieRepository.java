package com.acs560.HW4_REST_API.repositories;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.acs560.HW4_REST_API.exception.MoviesRepositoryManagementException;
import com.acs560.HW4_REST_API.models.Movie;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import lombok.Getter;
import lombok.Builder;
import org.springframework.stereotype.Repository;

/**
 * Repository class for managing movie data. This class is responsible for
 * reading movie data from a CSV file, storing it in memory, and providing
 * various methods to retrieve and analyze movie information.
 */
@Repository
@Getter
@Builder
public class MovieRepository {

    private static final String FILE_NAME = "netflix_data.csv";
    private static List<Movie> movies;

    public MovieRepository() {
        this.movies = initializeMovies();
    }

    /**
     * Retrieves the list of movies, initializing the list if it is not already
     * initialized. The list of movies is populated from the CSV file specified by
     * {@code FILE_NAME} if it is not already loaded.
     * 
     * @return List of movies objects with its attributes including title, director,
     *         type, and release year.
     */
    public List<Movie> getMovies() {
        if (movies == null) {
            movies = initializeMovies();
        }
        return movies;
    }

    /**
     * Initializes the list of movies by reading data from the CSV file. Each row in
     * the CSV file is parsed to create Movie instances. The first row is
     * skipped assuming it contains header information.
     * 
     * @return List of movies with its attributes including title, director, type,
     *         and release year.
     */
    private List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();
        try (FileReader fileReader = new FileReader(FILE_NAME);
             CSVReader reader = new CSVReaderBuilder(fileReader).withSkipLines(1).build()) {
            List<String[]> allData = reader.readAll();

            for (String[] row : allData) {
                if (row.length >= 5) {
                    Movie movie = Movie.builder().title(row[0]).director(row[1]).type(row[2]).countries(row[3])
                            .releaseYear(parseYear(row[4])).build();
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * Parses a string to an integer for the release year, with error handling for
     * invalid formats.
     * 
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
     * Find movies by title.
     * 
     * @param title The title of the movies to search for.
     * @return List of Movie objects released with the specified title.
     */
    public List<Movie> findByTitle(String title) {
        return getMovies().stream().filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    /**
     * Find movies by director.
     * 
     * @param director The director of the movies to search for.
     * @return List of Movie objects released by the specified director.
     */
    public List<Movie> findByDirector(String director) {
        return getMovies().stream().filter(movie -> movie.getDirector().equalsIgnoreCase(director))
                .collect(Collectors.toList());
    }

    /**
     * Find movies by type.
     * 
     * @param type The type of the movies to search for.
     * @return List of Movie objects of the specified type.
     */
    public List<Movie> findByType(String type) {
        return getMovies().stream().filter(movie -> movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Find movies by release year.
     * 
     * @param releaseYear The release year of the movies to search for.
     * @return List of Movie objects released in the specified year.
     */
    public List<Movie> findByReleaseYear(int releaseYear) {
        return getMovies().stream().filter(movie -> movie.getReleaseYear() == releaseYear).collect(Collectors.toList());
    }

    /**
     * Find movies by director and type.
     * 
     * @param director The director of the movies to search for.
     * @param type     The type of the movies to search for.
     * @return List of Movie objects that match the specified director and type.
     */
    public List<Movie> findByDirectorType(String director, String type) {
        return getMovies().stream().filter(
                movie -> movie.getDirector().equalsIgnoreCase(director) && movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Find movies by release year and type.
     * 
     * @param releaseYear The release year of the movies to search for.
     * @param type        The type of the movies to search for.
     * @return List of Movie objects that match the specified release year and type.
     */
    public List<Movie> findByYearType(int releaseYear, String type) {
        return getMovies().stream()
                .filter(movie -> movie.getReleaseYear() == releaseYear && movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Find movies by director, release year, and type.
     * 
     * @param director    The director of the movies to search for.
     * @param releaseYear The release year of the movies to search for.
     * @param type        The type of the movies to search for.
     * @return List of Movie objects that match the specified director,
     *         release year, and type.
     */
    public List<Movie> findByDirectorYearType(String director, int releaseYear, String type) {
        return getMovies()
                .stream().filter(movie -> movie.getDirector().equalsIgnoreCase(director)
                        && movie.getReleaseYear() == releaseYear && movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Get the count of total Movies and TV Shows.
     * 
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
     * Calculates the average number of movies per year and the total number of
     * movies. This method aggregates movie data by year and calculates the average
     * number of movies per year based on the total number of movies.
     * 
     * @return A string summarizing the total number of movies and the average
     *         number of movies per year.
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
        textResult.append(String.format("%-25s %s%n", "Total Movies:", "Average Movies per Year"));
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
     * 
     * @return A string summarizing the unique countries and the count of movies for
     *         each country.
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

    /**
     * Creates a {@link StatefulBeanToCsv} writer for writing Movie objects
     * to a CSV file.
     * 
     * @param writer The FileWriter object to write the CSV data to.
     * @return A {@link StatefulBeanToCsv} instance configured for writing movies.
     */
    private static StatefulBeanToCsv<Movie> createWriter(FileWriter writer) {
        ColumnPositionMappingStrategy<Movie> mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(Movie.class);
        mappingStrategy.setColumnMapping(new String[]{"title", "director", "type", "countries", "releaseYear"});

        return new StatefulBeanToCsvBuilder<Movie>(writer)
                .withMappingStrategy(mappingStrategy)
                .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .build();
    }

    /**
     * Adds a new movie to the repository and saves the updated list to the CSV file.
     * 
     * @param movie The Movie object to be added.
     * @throws MoviesRepositoryManagementException If the movie already exists or if there is an error saving the movie.
     */
    public static void addMovie(Movie movie) throws MoviesRepositoryManagementException {
        if (movies.stream().anyMatch(existingMovie -> existingMovie.equals(movie))) {
            throw new IllegalArgumentException("Error adding movie - movie already exists");
        }

        movies.add(movie);

        if (!saveMovies()) {
            throw new MoviesRepositoryManagementException("Error adding movie");
        }
    }

    /**
     * Saves a single movie to the CSV file. This method is no longer used as 
     * `saveMovies` handles saving all movies.
     * 
     * @param movie The Movie object to be saved.
     * @return true if the movie was saved successfully, false otherwise.
     */
    private static boolean saveMovie(Movie movie) {
        boolean isSaved = false;

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            StatefulBeanToCsv<Movie> beanWriter = createWriter(writer);
            beanWriter.write(movie);
            isSaved = true;
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.err.println(e.getMessage());
        }
        return isSaved;
    }

    /**
     * Saves the entire list of movies to the CSV file, including headers. This method
     * overwrites the existing file or creates a new file if it doesn't exist.
     * 
     * @return true if the movies were saved successfully, false otherwise.
     */
    private static boolean saveMovies() {
        boolean isSaved = false;

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            StatefulBeanToCsv<Movie> beanWriter = createWriter(writer);

            writer.write("title,director,type,countries,releaseYear\n");

            beanWriter.write(movies);
            isSaved = true;
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.err.println(e.getMessage());
        }

        return isSaved;
    }



    /**
     * Updates an existing movie in the repository and saves the updated list to the CSV file.
     * 
     * @param updateMovie The Movie object with updated information.
     * @throws MoviesRepositoryManagementException If the movie to update does not exist or if there is an error saving the updated list.
     */
    public static void updateMovie(Movie updateMovie) {
        if (updateMovie == null) {
            throw new IllegalArgumentException("The movie to update cannot be null.");
        }

        int index = -1;
        for (int i = 0; i < movies.size(); i++) {
            Movie existingMovie = movies.get(i);
            if (existingMovie.getTitle().equals(updateMovie.getTitle())) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IllegalArgumentException("Error updating movie: Movie does not exist.");
        }

        movies.set(index, updateMovie);
        if (!saveMovies()) {
            throw new MoviesRepositoryManagementException("Error saving updated movie.");
        }
    }

    /**
     * Deletes a movie from the repository and saves the updated list to the CSV file.
     * 
     * @param deleteMovie The Movie object to be deleted.
     * @throws MoviesRepositoryManagementException If the movie does not exist or if there is an error saving the updated list.
     */
    public static void deleteMovie(Movie deleteMovie) {
        if (deleteMovie == null) {
            throw new IllegalArgumentException("The movie to delete cannot be null.");
        }

        boolean movieFound = false;
        for (int i = 0; i < movies.size(); i++) {
            Movie existingMovie = movies.get(i);
            if (existingMovie.getTitle().equalsIgnoreCase(deleteMovie.getTitle()) &&
                existingMovie.getDirector().equalsIgnoreCase(deleteMovie.getDirector())) {
                movies.remove(i);
                movieFound = true;
                break;
            }
        }

        if (!movieFound) {
            throw new IllegalArgumentException("Error deleting movie: Movie does not exist.");
        }

        if (!saveMovies()) {
            throw new MoviesRepositoryManagementException("Error saving updated movie list.");
        }
    }


    /**
     * Find movies by release year between a specified range.
     * 
     * @param startYear The starting year of the range.
     * @param endYear The ending year of the range.
     * @return List of Movie objects released between the specified years.
     * @throws IllegalArgumentException if endYear is less than startYear
     */
    public List<Movie> findMoviesByReleaseYearBetween(int startYear, int endYear) {
        if (endYear < startYear) {
            throw new IllegalArgumentException("End year must be greater than or equal to start year");
        }

        return getMovies().stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }

}
