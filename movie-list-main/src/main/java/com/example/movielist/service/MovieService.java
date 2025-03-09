package com.example.movielist.service;

import com.example.movielist.model.MovieRequestModel;
import org.springframework.http.ResponseEntity;

public interface MovieService {
    ResponseEntity<Object> createMovie(MovieRequestModel movieRequestModel);
    ResponseEntity<Object>getMovieDetails(String movieName);
    ResponseEntity<Object>searchMovie(String title,String cast,String category);
}
