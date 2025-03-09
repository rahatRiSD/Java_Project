package com.example.movielist.controller;

import com.example.movielist.model.MovieRequestModel;
import com.example.movielist.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @PostMapping("/createMovie")
    public ResponseEntity<Object> createMovie(@RequestBody MovieRequestModel movieRequestModel){
        return movieService.createMovie(movieRequestModel);
    }
    @GetMapping("/getMovieDetails/{name}")
    public ResponseEntity<Object>getMovieDetails(@PathVariable String movieName){
        return movieService.getMovieDetails(movieName);
    }
    @GetMapping("/search/{titleOrCastOrCategory}")
    public ResponseEntity<Object>searchMovie(@PathVariable String title,String cast,String category){
        return movieService.searchMovie(title,cast,category);
    }

}
