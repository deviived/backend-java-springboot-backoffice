package com.deviived.angularbackofficebackend.controller;

import com.deviived.angularbackofficebackend.dto.MovieDTO;
import com.deviived.angularbackofficebackend.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class) // Load only the MovieController for testing
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Test
    public void testGetMovies() throws Exception {
        var list = new ArrayList<MovieDTO>();
        list.add(MovieDTO.builder().id(1L).name("Interstellar")
                .director("Christopher Nolan")
                .movieYear(2014)
                .rating(5d).build());
        when(movieService.findAll()).thenReturn(list);
        mockMvc.perform(get("/api/movies/"))
                .andExpect(status().isOk()) // Check HTTP 200 status
                .andExpect(content().contentType("application/json")) // Check response content type
                .andExpect(jsonPath("$.length()").value(1)) // Check JSON array length
                .andExpect(jsonPath("$[0].id").value(1)) // Check first movie's id
                .andExpect(jsonPath("$[0].name").value("Interstellar"))
                .andExpect(jsonPath("$[0].director").value("Christopher Nolan"))
                .andExpect(jsonPath("$[0].rating").value(5));
    }
}