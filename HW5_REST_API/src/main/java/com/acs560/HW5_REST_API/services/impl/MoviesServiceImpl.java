package com.acs560.HW5_REST_API.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.HW5_REST_API.entities.MovieEntity;
import com.acs560.HW5_REST_API.models.Movie;
import com.acs560.HW5_REST_API.repositories.MovieRepository;
import com.acs560.HW5_REST_API.services.MoviesService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of MoviesService interface.
 */
@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieves movies by ID.
     *
     * @param id The ID of the movie.
     * @return List containing the MovieEntity if found.
     */
    @Override
    public List<MovieEntity> getMoviesById(int id) {
        return movieRepository.findById(id);
    }

    /**
     * Retrieves all movies.
     *
     * @return List of all MovieEntity.
     */
    @Override
    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    /**
     * Retrieves movies by title.
     *
     * @param title The title to search for.
     * @return List of MovieEntity matching the title.
     */
    @Override
    public List<MovieEntity> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    /**
     * Retrieves movies by director.
     *
     * @param director The director to search for.
     * @return List of MovieEntity matching the director.
     */
    @Override
    public List<MovieEntity> getMoviesByDirector(String director) {
        return movieRepository.findByDirector(director);
    }

    /**
     * Retrieves movies by type.
     *
     * @param type The type to search for.
     * @return List of MovieEntity matching the type.
     */
    @Override
    public List<MovieEntity> getMoviesByType(String type) {
        return movieRepository.findByType(type);
    }

    /**
     * Retrieves movies by release year.
     *
     * @param releaseYear The release year to search for.
     * @return List of MovieEntity matching the release year.
     */
    @Override
    public List<MovieEntity> getMoviesByReleaseYear(int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    /**
     * Adds a new movie.
     *
     * @param movie The Movie model to add.
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
     * Updates an existing movie.
     *
     * @param id The ID of the movie to update.
     * @param movie The Movie model with updated details.
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
     * Deletes a movie by ID.
     *
     * @param id The ID of the movie to delete.
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
     * Converts a Movie model to MovieEntity.
     *
     * @param movie The Movie model.
     * @return The MovieEntity.
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
     * Converts a MovieEntity to Movie model.
     *
     * @param entity The MovieEntity.
     * @return The Movie model.
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
