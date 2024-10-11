package com.acs560.HW6_REST_API.entities;

import com.acs560.HW6_REST_API.models.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents the Type entity in the persistence layer.
 */
@Entity
@Table(name = "type")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TypeEntity {

    @Id
    private Integer typeId;

    @Column(unique = true)
    private String type;

    public TypeEntity(Type c) {
        this(c.getTypeId(), c.getType());
    }
    
    public TypeEntity(int typeId) {
        this.typeId = typeId;
    }
}
