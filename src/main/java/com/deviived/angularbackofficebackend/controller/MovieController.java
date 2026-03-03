package com.deviived.angularbackofficebackend.controller;

import com.deviived.angularbackofficebackend.dto.MovieDTO;
import com.deviived.angularbackofficebackend.enums.MovieGenre;
import com.deviived.angularbackofficebackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

    @PostMapping({"", "/"})
    @ResponseBody
    public MovieDTO saveMovie(@RequestBody final MovieDTO movie) {
        return this.movieService.save(movie);
    }

    @GetMapping("/{genre}")
    public List<MovieDTO> getMovies(@PathVariable("genre") final String genre) {
        return this.movieService.findAllByGenre(genre);
    }

    @GetMapping("/genres")
    public Map<String, String> getAllGenres() {
        return Stream.of(MovieGenre.values())
                .collect(Collectors.toMap(
                        MovieGenre::name,
                        MovieGenre::getLabel
                ));
    }

    @GetMapping("/exception")
    public String throwException() {
        throw new RuntimeException("Test exception occurred");
    }

}
