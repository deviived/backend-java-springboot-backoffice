package com.deviived.angularbackofficebackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
    private String name;
    private String director;
    private Integer year;
    private Double rating;
}
