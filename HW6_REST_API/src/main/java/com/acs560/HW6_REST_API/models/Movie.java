package com.acs560.HW6_REST_API.models;

import com.acs560.HW6_REST_API.entities.TypeEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {
    private int id;
    private String title;
    private String director;
    private int releaseYear;
    private String countries;
    private int typeId;

    public int getTypeId() {
        return typeId;
    }

	public void setTypeId(int typeId) {
		this.typeId = typeId;
		
	}

}

