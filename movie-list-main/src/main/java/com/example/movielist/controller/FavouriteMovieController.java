package com.example.movielist.controller;

import com.example.movielist.service.FavouriteMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favouriteMovie")
@RequiredArgsConstructor
public class FavouriteMovieController {
    private final FavouriteMovieService favouriteMovieService;
    @PostMapping("/addFavouriteMovie")
    public void addFavoriteMovie(@RequestParam Long userId, @RequestParam Long movieId) {
        favouriteMovieService.addFavoriteMovie(userId, movieId);
    }
    @DeleteMapping("/removeFavouriteMovie")
    public void removeFavoriteMovie(@RequestParam Long userId, @RequestParam Long movieId) {
        favouriteMovieService.removeFavoriteMovie(userId, movieId);
    }
    @GetMapping("/userFavouriteMovie/{userId}")
    public ResponseEntity<Object> getUserFavoriteMovies(@PathVariable Long userId) {
        return favouriteMovieService.getUserFavoriteMovies(userId);
    }
}
