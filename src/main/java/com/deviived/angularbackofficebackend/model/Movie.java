package com.deviived.angularbackofficebackend.model;

import com.deviived.angularbackofficebackend.enums.MovieGenre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    @Enumerated(EnumType.STRING)
    private MovieGenre genre;
    private Integer movieYear;
    private Double rating;
    @Column(name="number_of_reviews", columnDefinition = "integer default 0")
    private Integer totalReviews;
}
