package com.acs560.HW6_REST_API.models;

import com.acs560.HW6_REST_API.entities.TypeEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Type {
    private int typeId;
    private String type;

    public Type(TypeEntity type) {
        this.typeId = type.getTypeId();
        this.type = type.getType();
    }
}
