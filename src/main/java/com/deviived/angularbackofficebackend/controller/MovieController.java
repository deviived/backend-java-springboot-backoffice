package com.deviived.angularbackofficebackend.controller;

import com.deviived.angularbackofficebackend.dto.MovieDTO;
import com.deviived.angularbackofficebackend.enums.MovieGenre;
import com.deviived.angularbackofficebackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping({"", "/"})
    public List<MovieDTO> getMovies() {
        return this.movieService.findAll();
    }

    @GetMapping("/genres")
    public List<String[]> getAllGenres() {
        return Stream.of(MovieGenre.values())
                .map(genre -> new String[]{genre.name(), genre.getLabel()})
                .toList(); // Requires Java 16+
    }

    @GetMapping("/exception")
    public String throwException() {
        throw new RuntimeException("Test exception occurred");
    }

}
