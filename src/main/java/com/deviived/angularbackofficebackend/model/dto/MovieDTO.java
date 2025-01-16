package com.deviived.angularbackofficebackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String name;
    private String director;
    @JsonProperty
    private Integer movieYear;
    private Double rating;
    private Integer totalReviews;
}
