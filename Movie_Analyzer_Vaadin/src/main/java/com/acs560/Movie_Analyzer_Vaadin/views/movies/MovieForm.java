package com.acs560.Movie_Analyzer_Vaadin.views.movies;

import com.acs560.Movie_Analyzer_Vaadin.models.Movie;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import lombok.Getter;

/**
 * A form for managing movie details in the Movies Analyzer application.
 * <p>
 * This form allows users to create, update, or delete movie entries. It
 * provides fields for entering the movie title, director, release year,
 * type ID, and countries associated with the movie. The form supports
 * validation and handles save, delete, and cancel actions.
 * </p>
 */
public class MovieForm extends FormLayout {

    private static final long serialVersionUID = 1L;

    private final TextField title = new TextField("Title");
    private final TextField director = new TextField("Director");
    private final DatePicker releaseYear = new DatePicker("Release Year");
    private final TextField typeId = new TextField("Type ID");
    private final TextField countries = new TextField("Countries");
    
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button cancel = new Button("Cancel");

    private final Binder<Movie> binder = new BeanValidationBinder<>(Movie.class);
    private Movie movie;
    private boolean isAdd;

    /**
     * Constructs a new {@code MovieForm} instance.
     * <p>
     * Initializes the layout, binds fields to the movie model, and adds UI components
     * including the title, director, release year, type ID, and countries.
     * </p>
     */
    public MovieForm() {
        addClassName("movie-form");
        binder.bindInstanceFields(this);
        add(title, director, releaseYear, typeId, countries, createButtonsLayout());
        setWidth("25em");
    }

    /**
     * Creates a layout containing action buttons for the form.
     * <p>
     * Sets up button themes, shortcuts, and event listeners for save, delete, and cancel actions.
     * </p>
     * 
     * @return a layout containing the action buttons
     */
    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> handleSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        cancel.addClickListener(event -> fireEvent(new CancelEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, cancel);
    }

    /**
     * Handles the save action for the form.
     * <p>
     * Writes the bean data from the binder and fires an event to indicate
     * whether a movie was added or updated.
     * </p>
     */
    private void handleSave() {
        try {
            binder.writeBean(movie);
            if (isAdd) {
                fireEvent(new AddEvent(this, movie));
            } else {
                fireEvent(new UpdateEvent(this, movie));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the form with a given movie instance and sets the form mode (add or edit).
     * 
     * @param movie the movie to be managed by the form
     * @param isAdd indicates if the form is in add mode
     */
    public void update(Movie movie, boolean isAdd) {
        this.isAdd = isAdd;
        delete.setVisible(!isAdd);

        if (movie != null) {
            this.movie = movie;
        } else {
            title.clear();
            director.clear();
            releaseYear.clear();
            this.movie = new Movie();
        }

        binder.setBean(this.movie);
    }

    /**
     * Base class for events generated by the {@code MovieForm}.
     */
    public static abstract class MovieFormEvent extends ComponentEvent<MovieForm> {

        private static final long serialVersionUID = 1L;

        @Getter
        private Movie movie;

        protected MovieFormEvent(MovieForm source, Movie movie) {
            super(source, false);
            this.movie = movie;
        }
    }

    /**
     * Event for adding a new movie.
     */
    public static class AddEvent extends MovieFormEvent {
        private static final long serialVersionUID = 1L;

        protected AddEvent(MovieForm source, Movie movie) {
            super(source, movie);
        }
    }

    /**
     * Event for updating an existing movie.
     */
    public static class UpdateEvent extends MovieFormEvent {
        private static final long serialVersionUID = 1L;

        protected UpdateEvent(MovieForm source, Movie movie) {
            super(source, movie);
        }
    }

    /**
     * Event for deleting a movie.
     */
    public static class DeleteEvent extends MovieFormEvent {
        private static final long serialVersionUID = 1L;

        protected DeleteEvent(MovieForm source, Movie movie) {
            super(source, movie);
        }
    }

    /**
     * Event for canceling the form action.
     */
    public static class CancelEvent extends MovieFormEvent {
        private static final long serialVersionUID = 1L;

        protected CancelEvent(MovieForm source) {
            super(source, null);
        }
    }

    /**
     * Adds a listener for movie form events.
     * 
     * @param <T> the type of event
     * @param eventType the class of the event type
     * @param listener the listener to be added
     * @return a registration for the listener
     */
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}