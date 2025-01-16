package com.deviived.angularbackofficebackend.services;

import com.deviived.angularbackofficebackend.mappers.MovieMapper;
import com.deviived.angularbackofficebackend.model.dto.MovieDTO;
import com.deviived.angularbackofficebackend.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public List<MovieDTO> findAll() {
        var movies = this.movieRepository.findAll();
        var moviesDTO = new ArrayList<MovieDTO>();

        movies.forEach(movie -> moviesDTO.add(MovieMapper.INSTANCE.movieToDto(movie)));
        return moviesDTO;
    }
}
