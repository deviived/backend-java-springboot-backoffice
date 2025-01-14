package com.deviived.angularbackofficebackend.controllers;

import com.deviived.angularbackofficebackend.model.dto.MovieDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/movies")
public class MovieController {

    @GetMapping("/")
    public List<MovieDTO> index() {
        return new ArrayList<>();
        /*return "Greetings from Spring Boot! Yeah!";*/
    }

}
