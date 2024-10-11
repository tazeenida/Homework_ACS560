package com.acs560.HW6_REST_API.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.HW6_REST_API.entities.TypeEntity;
import com.acs560.HW6_REST_API.repositories.TypeRepository;
import com.acs560.HW6_REST_API.services.TypeService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Implementation of the {@link TypeService} interface.
 * <p>
 * This service class handles the business logic related to movie types, 
 * providing methods to retrieve, add, update, and delete type data. 
 * It interacts with the {@link TypeRepository} for data access.
 * </p>
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    /**
     * Retrieves all types from the repository.
     *
     * @return List of all {@link TypeEntity} objects stored in the repository.
     */
    @Override
    public List<TypeEntity> getAllTypes() {
        return StreamSupport.stream(typeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a type from the repository by its ID.
     *
     * @param id The ID of the type to retrieve.
     * @return The {@link TypeEntity} associated with the provided ID.
     * @throws IllegalArgumentException if the type is not found with the given ID.
     */
    @Override
    public TypeEntity getTypeById(int id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found with ID: " + id));
    }

    /**
     * Adds a new type to the repository.
     *
     * @param typeEntity The {@link TypeEntity} to add.
     */
    @Override
    public void addType(TypeEntity typeEntity) {
        typeRepository.save(typeEntity);
    }

    /**
     * Updates an existing type in the repository.
     *
     * @param id         The ID of the type to update.
     * @param typeEntity The {@link TypeEntity} with updated details.
     * @throws IllegalArgumentException if the type does not exist with the given ID.
     */
    @Override
    public void updateType(int id, TypeEntity typeEntity) {
        if (!typeRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot update. Type not found with ID: " + id);
        }

        typeEntity.setTypeId(id); // Now you can set the typeId
        typeRepository.save(typeEntity);
    }

    /**
     * Deletes a type from the repository by its ID.
     *
     * @param id The ID of the type to delete.
     * @throws IllegalArgumentException if the type does not exist with the given ID.
     */
    @Override
    public void deleteType(int id) {
        if (!typeRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete. Type not found with ID: " + id);
        }
        typeRepository.deleteById(id);
    }
}
