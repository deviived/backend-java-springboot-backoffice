package com.deviived.angularbackofficebackend.services;

import com.deviived.angularbackofficebackend.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Test
    public void testGetEmptyMovies() {
        var movies = this.movieService.findAll();
        Assertions.assertNotNull(movies);
        Assertions.assertTrue(movies.isEmpty());
    }

    @Test
    @Transactional
    @Sql(statements = "INSERT INTO movie (id, name, director, movie_year, rating) VALUES (1, 'Interstellar', 'Christopher Nolan', 2014, 4.9)",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testGetMovies() {
        var movies = this.movieService.findAll();
        Assertions.assertNotNull(movies);
        Assertions.assertFalse(movies.isEmpty());
        Assertions.assertEquals(2014, movies.getFirst().getMovieYear());
        Assertions.assertEquals("Interstellar", movies.getFirst().getName());
        Assertions.assertEquals("Christopher Nolan", movies.getFirst().getDirector());
        Assertions.assertEquals(4.9, movies.getFirst().getRating());
    }

}
