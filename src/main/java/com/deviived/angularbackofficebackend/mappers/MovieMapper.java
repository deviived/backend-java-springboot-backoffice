package com.deviived.angularbackofficebackend.mappers;

import com.deviived.angularbackofficebackend.model.dto.MovieDTO;
import com.deviived.angularbackofficebackend.model.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper( MovieMapper.class );

    MovieDTO movieToDto(Movie movie);
}