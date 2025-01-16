package com.deviived.angularbackofficebackend.controllers;

import com.deviived.angularbackofficebackend.model.dto.MovieDTO;
import com.deviived.angularbackofficebackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping({"", "/"})
    public List<MovieDTO> getMovies() {
        return this.movieService.findAll();
    }

}
