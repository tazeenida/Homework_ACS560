package com.acs560.HW6_REST_API.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.HW6_REST_API.entities.MovieEntity;
import com.acs560.HW6_REST_API.models.Movie;
import com.acs560.HW6_REST_API.repositories.MovieRepository;
import com.acs560.HW6_REST_API.services.MoviesService;

import java.util.List;

/**
 * Implementation of the {@link MoviesService} interface.
 * <p>
 * This service class handles business logic related to movies,
 * providing methods to retrieve, add, update, and delete movie data.
 * It interacts with the {@link MovieRepository} for data access.
 * </p>
 */
@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieves movies from the repository by their ID.
     *
     * @param id The ID of the movie to retrieve.
     * @return List containing the MovieEntity associated with the provided ID.
     */
    @Override
    public List<MovieEntity> getMoviesById(int id) {
        return movieRepository.findById(id);
    }

    /**
     * Retrieves all movies from the repository.
     *
     * @return List of all MovieEntity objects stored in the repository.
     */
    @Override
    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    /**
     * Retrieves movies by their title.
     *
     * @param title The title to search for.
     * @return List of MovieEntity objects matching the specified title.
     */
    @Override
    public List<MovieEntity> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    /**
     * Retrieves movies by their director.
     *
     * @param director The director to search for.
     * @return List of MovieEntity objects directed by the specified director.
     */
    @Override
    public List<MovieEntity> getMoviesByDirector(String director) {
        return movieRepository.findByDirector(director);
    }

    /**
     * Retrieves movies by their type (e.g., Action, Comedy).
     *
     * @param type The type to search for.
     * @return List of MovieEntity objects matching the specified type.
     */
    @Override
    public List<MovieEntity> getMoviesByType(String type) {
        return movieRepository.findByType(type);
    }

    /**
     * Retrieves movies by their release year.
     *
     * @param releaseYear The release year to search for.
     * @return List of MovieEntity objects released in the specified year.
     */
    @Override
    public List<MovieEntity> getMoviesByReleaseYear(int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    /**
     * Adds a new movie to the repository.
     *
     * @param movie The Movie model containing details of the movie to add.
     * @throws IllegalArgumentException if a movie with the same title, director, and release year already exists.
     */
    public void add(Movie movie) {
        List<MovieEntity> existingMovies = movieRepository.findByTitleAndDirectorAndReleaseYear(
                movie.getTitle(),
                movie.getDirector(),
                movie.getReleaseYear()
        );
        if (!existingMovies.isEmpty()) {
            throw new IllegalArgumentException("Movie already exists with Title: " 
                + movie.getTitle() + ", Director: " + movie.getDirector() 
                + ", Release Year: " + movie.getReleaseYear());
        }

        MovieEntity movieEntity = convertToEntity(movie);
        movieRepository.save(movieEntity);
    }

    /**
     * Updates an existing movie in the repository.
     *
     * @param id The ID of the movie to update.
     * @param movie The Movie model with updated details.
     * @throws IllegalArgumentException if no movie with the specified ID exists.
     */
    @Override
    public void update(int id, Movie movie) {
        // Check if a movie with the given ID exists
        if (!movieRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot update. Movie not found with ID: " + id);
        }

        // Set the movie ID to the one passed in
        movie.setId(id);

        // Convert the Movie DTO to the MovieEntity and save the updated entity
        MovieEntity movieEntity = convertToEntity(movie);

        // Save the updated movie entity back to the database
        movieRepository.save(movieEntity);
    }

    /**
     * Deletes a movie from the repository by its ID.
     *
     * @param id The ID of the movie to delete.
     * @throws IllegalArgumentException if no movie with the specified ID exists.
     */
    @Override
    public void delete(int id) {
        // Check if the movie exists
        if (!movieRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete. Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }

    /**
     * Converts a Movie model to a MovieEntity.
     *
     * @param movie The Movie model to convert.
     * @return The corresponding MovieEntity.
     */
    private MovieEntity convertToEntity(Movie movie) {
        return new MovieEntity(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getType(),
                movie.getCountries(),
                movie.getReleaseYear()
        );
    }

    /**
     * Converts a MovieEntity to a Movie model.
     *
     * @param entity The MovieEntity to convert.
     * @return The corresponding Movie model.
     */
    private Movie convertToModel(MovieEntity entity) {
        Movie movie = new Movie();
        movie.setId(entity.getId());
        movie.setTitle(entity.getTitle());
        movie.setDirector(entity.getDirector());
        movie.setType(entity.getType());
        movie.setCountries(entity.getCountries());
        movie.setReleaseYear(entity.getReleaseYear());
        return movie;
    }
}