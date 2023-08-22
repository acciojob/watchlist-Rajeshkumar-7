package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("New Movie added successfully" , HttpStatus.CREATED );
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("New Director added successfully" , HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName ,@RequestParam("director") String directorName){
        movieService.addMovieDirectorPair(movieName , directorName);
        return new ResponseEntity<>("New Movie and Director pair added successfully" , HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie , HttpStatus.CREATED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name){
        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director , HttpStatus.CREATED);
    }

    @GetMapping("/get-movies-by-director-name/{name}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("name") String name){
        List<String> movies = movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<>(movies , HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String name){
        movieService.deleteDirectorByName(name);
        return new ResponseEntity<>("All records of the Director and his movies has been deleted" , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All Director and his movies has been deleted" , HttpStatus.CREATED);
    }
}
