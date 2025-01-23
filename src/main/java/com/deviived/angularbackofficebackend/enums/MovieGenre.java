package com.deviived.angularbackofficebackend.enums;

import lombok.Getter;

@Getter
public enum MovieGenre {
    ACTION("Action"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    THRILLER("Thriller"),
    WESTERN("Western");

    private final String label;

    MovieGenre(String label) {
        this.label = label;
    }
}