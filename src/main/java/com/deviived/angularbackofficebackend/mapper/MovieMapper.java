package com.deviived.angularbackofficebackend.mapper;

import com.deviived.angularbackofficebackend.dto.MovieDTO;
import com.deviived.angularbackofficebackend.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper( MovieMapper.class );

    MovieDTO movieToDto(Movie movie);
    Movie movieToEntity(MovieDTO movie);
}