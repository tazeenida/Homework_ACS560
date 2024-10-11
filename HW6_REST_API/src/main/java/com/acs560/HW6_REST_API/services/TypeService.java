package com.acs560.HW6_REST_API.services;

import com.acs560.HW6_REST_API.entities.TypeEntity;

import java.util.List;

/**
 * Service interface for managing movie types.
 * <p>
 * This interface defines the methods for performing CRUD operations
 * on movie types, including retrieving, adding, updating, and deleting
 * types in the persistence layer.
 * </p>
 */
public interface TypeService {

    /**
     * Retrieves all types from the repository.
     *
     * @return A list of all {@link TypeEntity} objects stored in the repository.
     */
    List<TypeEntity> getAllTypes();

    /**
     * Retrieves a type by its ID.
     *
     * @param id The ID of the type to retrieve.
     * @return The {@link TypeEntity} associated with the provided ID.
     * @throws IllegalArgumentException if no type with the specified ID exists.
     */
    TypeEntity getTypeById(int id);

    /**
     * Adds a new type to the repository.
     *
     * @param typeEntity The {@link TypeEntity} to add.
     * @throws IllegalArgumentException if a type with the same name already exists.
     */
    void addType(TypeEntity typeEntity);

    /**
     * Updates an existing type in the repository.
     *
     * @param id The ID of the type to update.
     * @param typeEntity The updated {@link TypeEntity}.
     * @throws IllegalArgumentException if no type with the specified ID exists.
     */
    void updateType(int id, TypeEntity typeEntity);

    /**
     * Deletes a type from the repository by its ID.
     *
     * @param id The ID of the type to delete.
     * @throws IllegalArgumentException if no type with the specified ID exists.
     */
    void deleteType(int id);
}
