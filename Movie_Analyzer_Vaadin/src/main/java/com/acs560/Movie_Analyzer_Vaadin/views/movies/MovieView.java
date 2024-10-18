package com.acs560.Movie_Analyzer_Vaadin.views.movies;

import com.acs560.Movie_Analyzer_Vaadin.entities.MovieEntity;
import com.acs560.Movie_Analyzer_Vaadin.models.Movie;
import com.acs560.Movie_Analyzer_Vaadin.services.MoviesService;
import com.acs560.Movie_Analyzer_Vaadin.views.MainLayout;
import com.acs560.Movie_Analyzer_Vaadin.services.TypeService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.annotation.SpringComponent;

import jakarta.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * The view for managing movies in the Movies Analyzer application.
 * <p>
 * This view allows users to add, update, and delete movie entries. It displays a grid
 * of movies and provides text fields for entering movie details such as title, director,
 * release year, type ID, and countries. The view interacts with the {@code MoviesService}
 * to perform operations on movie data.
 * </p>
 */
@AnonymousAllowed
@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "movies", layout = MainLayout.class)
@PageTitle("Movies | Movies Analyzer")
public class MovieView extends VerticalLayout {

    private final MoviesService moviesService;
    private final Grid<MovieEntity> grid = new Grid<>(MovieEntity.class);
    private final TextField titleField = new TextField("Title");
    private final TextField directorField = new TextField("Director");
    private final TextField releaseYearField = new TextField("Release Year");
    private final TextField typeIdField = new TextField("Type Id");
    private final TextField countriesField = new TextField("Countries");

    @Autowired
    private TypeService typeService;

    /**
     * Constructs a new {@code MovieView} instance.
     * <p>
     * Initializes the layout, binds the grid to the movie service, and adds UI components
     * including text fields and action buttons for managing movies.
     * </p>
     * 
     * @param moviesService the service used to manage movies
     */
    @Autowired
    public MovieView(MoviesService moviesService) {
        this.moviesService = moviesService;
        this.typeService = typeService;
        add(titleField, directorField, releaseYearField, typeIdField, countriesField,
            createAddButton(), createUpdateButton(), createDeleteButton(), grid);
        grid.setColumns("id", "title", "director", "releaseYear", "type.typeId", "countries");
        grid.asSingleSelect().addValueChangeListener(event -> {
            MovieEntity selectedMovie = event.getValue();
            if (selectedMovie != null) {
                titleField.setValue(selectedMovie.getTitle());
                directorField.setValue(selectedMovie.getDirector());
                releaseYearField.setValue(String.valueOf(selectedMovie.getReleaseYear()));
                typeIdField.setValue(selectedMovie.getType() != null ? String.valueOf(selectedMovie.getType().getTypeId()) : "");
                countriesField.setValue(selectedMovie.getCountries());
            }
        });

        refreshGrid();
    }

    /**
     * Creates a button for adding a new movie.
     * 
     * @return the created add button
     */
    private Button createAddButton() {
        Button addButton = new Button("Add Movie");
        addButton.addClickListener(e -> {
            addMovie();
        });
        return addButton;
    }

    /**
     * Adds a new movie based on the entered details in the text fields.
     * <p>
     * If the operation is successful, a notification is displayed, and the grid is refreshed.
     * </p>
     */
    private void addMovie() {
        try {
            Movie movie = new Movie();
            movie.setTitle(titleField.getValue());
            movie.setDirector(directorField.getValue());
            movie.setReleaseYear(Integer.parseInt(releaseYearField.getValue()));
            movie.setTypeId(Integer.parseInt(typeIdField.getValue()));
            movie.setCountries(countriesField.getValue());

            moviesService.add(movie);
            Notification.show("Movie added successfully!");
            refreshGrid();
        } catch (Exception e) {
            Notification.show(e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }

    /**
     * Refreshes the movie grid by retrieving the latest movie data from the service.
     */
    private void refreshGrid() {
        List<MovieEntity> movies = moviesService.getMovies();
        grid.setItems(movies);
    }

    /**
     * Creates a button for updating the selected movie.
     * 
     * @return the created update button
     */
    private Button createUpdateButton() {
        Button updateButton = new Button("Update Movie");
        updateButton.addClickListener(e -> {
            updateMovie();
        });
        return updateButton;
    }

    /**
     * Updates the selected movie based on the entered details in the text fields.
     * <p>
     * If the operation is successful, a notification is displayed, and the grid is refreshed.
     * </p>
     */
    private void updateMovie() {
        MovieEntity selectedMovie = grid.asSingleSelect().getValue();
        if (selectedMovie == null) {
            Notification.show("Select a movie to update");
            return;
        }

        try {
            Movie movie = new Movie();
            movie.setId(selectedMovie.getId());
            movie.setTitle(titleField.getValue());
            movie.setDirector(directorField.getValue());
            movie.setReleaseYear(Integer.parseInt(releaseYearField.getValue()));
            movie.setTypeId(Integer.parseInt(typeIdField.getValue()));
            movie.setCountries(countriesField.getValue());

            moviesService.update(selectedMovie.getId(), movie);
            Notification.show("Movie updated successfully!");
            refreshGrid();
        } catch (Exception e) {
            Notification.show(e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }

    /**
     * Creates a button for deleting the selected movie.
     * 
     * @return the created delete button
     */
    private Button createDeleteButton() {
        Button deleteButton = new Button("Delete Movie");
        deleteButton.addClickListener(e -> {
            deleteMovie();
        });
        return deleteButton;
    }

    /**
     * Deletes the selected movie.
     * <p>
     * If the operation is successful, a notification is displayed, and the grid is refreshed.
     * </p>
     */
    private void deleteMovie() {
        MovieEntity selectedMovie = grid.asSingleSelect().getValue();
        if (selectedMovie == null) {
            Notification.show("Select a movie to delete");
            return;
        }

        try {
            moviesService.delete(selectedMovie.getId());
            Notification.show("Movie deleted successfully!");
            refreshGrid();
        } catch (Exception e) {
            Notification.show(e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }
}
