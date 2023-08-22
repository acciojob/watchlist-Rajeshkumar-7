package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {

    private final HashMap<String , Movie> movieMap;
    private final HashMap<String , Director> directorMap;
    private final HashMap<String , List<String>> directorMovieMap;

    public MovieRepository() {
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.directorMovieMap = new HashMap<>();
    }


    public void addMovie(Movie movie) {
        movieMap.put(movie.getName() , movie);
    }

    public void addDirector(Director director) {
        directorMap.put(director.getName() , director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)){
            List<String> currentMovies = new ArrayList<>();
            if(directorMovieMap.containsKey(directorName)){
                currentMovies = directorMovieMap.get(directorName);
            }
            currentMovies.add(movieName);
            directorMovieMap.put(directorName , currentMovies);
        }
    }


    public Movie getMovieByName(String name) {
        return movieMap.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorMap.get(name);
    }

    public List<String> getMoviesByDirectorName(String name) {
        List<String> moviesList = new ArrayList<>();
        if(directorMovieMap.containsKey(name)){
            moviesList = directorMovieMap.get(name);
        }
        return directorMovieMap.get(name);
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirectorByName(String name) {

        List<String> movies = new ArrayList<>();
        if(directorMovieMap.containsKey(name)){
            movies = directorMovieMap.get(name);
            for (String movie : movies){
                movieMap.remove(movie);
            }

            directorMovieMap.remove(name);
        }

        directorMap.remove(name);
    }

    public void deleteAllDirectors() {

        HashSet<String> movies = new HashSet<>();
        for(String director : directorMovieMap.keySet()){
            for(String movie : directorMovieMap.get(director)){
                movies.add(movie);
            }
        }

        for(String movie : movies){
            movieMap.remove(movie);
        }

        directorMap.clear();
        directorMovieMap.clear();
    }
}
