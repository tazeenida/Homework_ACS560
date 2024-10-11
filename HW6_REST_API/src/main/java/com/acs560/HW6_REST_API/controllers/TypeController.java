package com.acs560.HW6_REST_API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acs560.HW6_REST_API.entities.TypeEntity;
import com.acs560.HW6_REST_API.services.TypeService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing movie types.
 * This controller provides endpoints for CRUD operations on movie types,
 * including retrieval, creation, updating, and deletion of types in the system.
 */
@RestController
@RequestMapping("/api/v1/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * Retrieves all types from the system.
     * 
     * @return ResponseEntity containing the list of all types.
     */
    @GetMapping
    public ResponseEntity<List<TypeEntity>> getAllTypes() {
        List<TypeEntity> types = typeService.getAllTypes();
        return ResponseEntity.ok(types);
    }

    /**
     * Retrieves a type by its unique ID.
     * 
     * @param id The unique ID of the type to retrieve.
     * @return ResponseEntity containing the type if found, or an error status if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TypeEntity> getTypeById(@PathVariable int id) {
        TypeEntity type = typeService.getTypeById(id);
        return ResponseEntity.ok(type);
    }
    
    /**
     * Retrieves a type by its name.
     *
     * @param type The name of the type to retrieve.
     * @return ResponseEntity containing the TypeEntity if found, or an error status if not.
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<TypeEntity> getByType(@PathVariable String type) {
        try {
            TypeEntity typeEntity = typeService.getByType(type);
            return ResponseEntity.ok(typeEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Return 404 if not found
        }
    }

    /**
     * Adds a new type to the system.
     * 
     * @param typeEntity The type details to add.
     * @return ResponseEntity with HTTP status indicating the result of the operation.
     */
    @PostMapping
    public ResponseEntity<String> addType(@RequestBody TypeEntity typeEntity) {
        typeService.addType(typeEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Type added successfully.");
    }

    /**
     * Updates an existing type in the system.
     * 
     * @param id The unique ID of the type to update.
     * @param typeEntity The updated type details.
     * @return ResponseEntity with HTTP status indicating the result of the update.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateType(@PathVariable int id, @RequestBody TypeEntity typeEntity) {
        typeService.updateType(id, typeEntity);
        return ResponseEntity.ok("Type updated successfully.");
    }

    /**
     * Deletes a type from the system by its ID.
     * 
     * @param id The unique ID of the type to delete.
     * @return ResponseEntity with HTTP status indicating the result of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteType(@PathVariable int id) {
        typeService.deleteType(id);
        return ResponseEntity.ok("Type deleted successfully.");
    }
}
