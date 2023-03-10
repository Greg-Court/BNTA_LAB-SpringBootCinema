package com.bnta.spring_cinema.services;

import com.bnta.spring_cinema.models.Movie;
import com.bnta.spring_cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public void createNewMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(Integer maxDuration) {
        if (maxDuration != null) {
            return movieRepository.findByDurationLessThan(maxDuration);
        } else return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) return movie.get();
        else return null;
    }

    public void updateMovie(int id, Movie newMovie) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            Movie existingMovie = movie.get();
            existingMovie.setTitle(newMovie.getTitle());
            existingMovie.setRating(newMovie.getRating());
            existingMovie.setDuration(newMovie.getDuration());
            movieRepository.save(existingMovie);
        }
    }

    public void deleteMovie(int id) {
        if (movieRepository.findById(id).isPresent()) movieRepository.delete(movieRepository.findById(id).get());
    }


}
