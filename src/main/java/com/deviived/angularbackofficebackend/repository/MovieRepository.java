package com.deviived.angularbackofficebackend.repository;

import com.deviived.angularbackofficebackend.model.entity.Movie;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends ListCrudRepository<Movie, Long> {
}
