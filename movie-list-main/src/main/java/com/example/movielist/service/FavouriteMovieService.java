package com.example.movielist.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavouriteMovieService {
    void addFavoriteMovie(Long userId, Long movieId);
    void removeFavoriteMovie(Long userId, Long movieId);
    ResponseEntity<Object> getUserFavoriteMovies(Long userId);
}
