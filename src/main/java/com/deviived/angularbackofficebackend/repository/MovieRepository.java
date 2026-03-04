package com.deviived.angularbackofficebackend.repository;

import com.deviived.angularbackofficebackend.enums.MovieGenre;
import com.deviived.angularbackofficebackend.model.Movie;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends ListCrudRepository<Movie, Long> {
  List<Movie> findAllByGenre(MovieGenre genre);
}
