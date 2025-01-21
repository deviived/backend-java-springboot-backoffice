package com.deviived.angularbackofficebackend.enums;

import lombok.Getter;

@Getter
public enum MovieGenre {
    ACTION("Action"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    HORROR("Horror"),
    SCIENCE_FICTION("Science Fiction"),
    THRILLER("Thriller"),
    ROMANCE("Romance"),
    FANTASY("Fantasy");

    private final String label;

    MovieGenre(String label) {
        this.label = label;
    }
}