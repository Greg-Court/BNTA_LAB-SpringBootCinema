package com.bnta.spring_cinema.repositories;

import com.bnta.spring_cinema.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByDurationLessThan(Integer maxDuration);
}

// take in 2 types in angle brackets - first, the class type for which we create the repository
// second, the datatype of the ID used by the class.