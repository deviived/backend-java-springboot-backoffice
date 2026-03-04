package com.deviived.angularbackofficebackend.service;

import com.deviived.angularbackofficebackend.enums.MovieGenre;
import com.deviived.angularbackofficebackend.mapper.MovieMapper;
import com.deviived.angularbackofficebackend.dto.MovieDTO;
import com.deviived.angularbackofficebackend.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public List<MovieDTO> findAll() {
        var movies = this.movieRepository.findAll();
        var moviesDTO = new ArrayList<MovieDTO>();

        movies.forEach(movie -> moviesDTO.add(MovieMapper.INSTANCE.movieToDto(movie)));
        return moviesDTO;
    }

    @Transactional
    public List<MovieDTO> findAllByGenre(final String genre) {
        var movies = this.movieRepository.findAllByGenre(MovieGenre.valueOf(genre.toUpperCase()));
        var moviesDTO = new ArrayList<MovieDTO>();

        movies.forEach(movie -> moviesDTO.add(MovieMapper.INSTANCE.movieToDto(movie)));
        return moviesDTO;
    }

    @Transactional
    public MovieDTO save(MovieDTO dto) {
        var entity = MovieMapper.INSTANCE.movieToEntity(dto);
        this.movieRepository.save(entity);
        return dto;
    }

}
