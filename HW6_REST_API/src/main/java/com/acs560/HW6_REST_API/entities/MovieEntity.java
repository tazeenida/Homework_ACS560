package com.acs560.HW6_REST_API.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "type")
@EqualsAndHashCode
public class MovieEntity {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String director;

    @ManyToOne // or @OneToMany, depending on your design
    @JoinColumn(name = "type_id") // Ensure the correct foreign key column name
    private TypeEntity type;

    @Column(nullable = true)
    private String countries;

    @Column(name = "release_year", nullable = true)
    private Integer releaseYear;

}
