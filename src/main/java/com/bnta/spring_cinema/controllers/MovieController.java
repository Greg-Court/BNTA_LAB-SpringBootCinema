package com.bnta.spring_cinema.controllers;

import com.bnta.spring_cinema.models.Movie;
import com.bnta.spring_cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    private Movie movie;

    @Autowired
    private MovieService movieService;

    // GET /movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) Integer maxDuration) {
        List<Movie> movies = movieService.getAllMovies(maxDuration);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


    // GET /movies/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie,HttpStatus.OK);
    }


    // POST /movies
    @PostMapping
    public ResponseEntity<String> newMovie(@RequestBody Movie movie) {
        movieService.createNewMovie(movie);
        return new ResponseEntity<>("Movie " + movie.getTitle() + " successfully created", HttpStatus.CREATED);
    }


    // PUT /movies/{id}
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable int id, @RequestBody Movie newMovie) {
        String oldMovieTitle = movieService.getMovieById(id).getTitle();
        movieService.updateMovie(id, newMovie);
        return new ResponseEntity<>("Movie " + oldMovieTitle + " successfully replaced with " + newMovie.getTitle(), HttpStatus.OK);
    }


    // DELETE / movies/{id}
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable int id) {
        String movieTitle = movieService.getMovieById(id).getTitle();
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie " + movieTitle + " successfully deleted", HttpStatus.OK);
    }

}
