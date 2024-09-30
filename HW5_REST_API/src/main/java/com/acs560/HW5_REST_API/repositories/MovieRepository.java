package com.acs560.HW5_REST_API.repositories;

import java.util.List;
import java.util.Optional;

import com.acs560.HW5_REST_API.entities.MovieEntity;
import com.acs560.HW5_REST_API.models.Movie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {
	List<MovieEntity> findAll();

    List<MovieEntity> findById(int id);

    List<MovieEntity> findByTitle(String title);

    List<MovieEntity> findByReleaseYear(int releaseYear);

    List<MovieEntity> findByDirector(String director);

    List<MovieEntity> findByType(String type);
    
    /**
     * Finds movies by title, director, and release year.
     * Used to check for duplicates.
     *
     * @param title        The title of the movie.
     * @param director     The director of the movie.
     * @param releaseYear  The release year of the movie.
     * @return List of MovieEntity matching the criteria.
     */
    List<MovieEntity> findByTitleAndDirectorAndReleaseYear(String title, String director, int releaseYear);
}

