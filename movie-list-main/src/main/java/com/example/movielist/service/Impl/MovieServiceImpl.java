package com.example.movielist.service.Impl;

import com.example.movielist.entity.Movie;
import com.example.movielist.model.MovieRequestModel;
import com.example.movielist.model.MovieResponseModel;
import com.example.movielist.repository.MovieRepository;
import com.example.movielist.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    @Autowired
    private final MovieRepository movieRepository;

    @Override
    public ResponseEntity<Object> createMovie(MovieRequestModel movieRequestModel) {
        Movie movie = Movie
                .builder()
                .title(movieRequestModel.getTitle())
                .cast(movieRequestModel.getCast())
                .category(movieRequestModel.getCategory())
                .releaseDate(movieRequestModel.getReleaseDate())
                .budget(movieRequestModel.getBudget())
                .build();
        Movie savedMovieDetails = movieRepository.save(movie);

        MovieResponseModel movieResponseModel = MovieResponseModel
                .builder()
                .id(savedMovieDetails.getId())
                .title(savedMovieDetails.getTitle())
                .cast(savedMovieDetails.getCast())
                .category(savedMovieDetails.getCategory())
                .releaseDate(savedMovieDetails.getReleaseDate())
                .budget(savedMovieDetails.getBudget())
                .build();
        return new ResponseEntity<>(movieResponseModel, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> getMovieDetails(String movieName) {
        Movie movie = movieRepository.findByTitle(movieName);
        if(movie != null){
            MovieResponseModel movieResponseModel = MovieResponseModel
                    .builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .cast(movie.getCast())
                    .category(movie.getCategory())
                    .releaseDate(movie.getReleaseDate())
                    .budget(movie.getBudget())
                    .build();
            return new ResponseEntity<>(movieResponseModel,HttpStatus.OK);
        }
        return new ResponseEntity<>(new RuntimeException("There is such a no book found on this name"),HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<Object> searchMovie(String title,String cast,String category) {
        List<Movie> MovieList = (List<Movie>) movieRepository.findByTitleContainingIgnoreCaseOrCastContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrderByTitleAsc(title,cast,category);
        List<MovieResponseModel> movieList = new ArrayList<>();
        for(Movie movie : MovieList){
            MovieResponseModel movieResponseModel = MovieResponseModel
                    .builder()
                    .title(movie.getTitle())
                    .build();
            movieList.add(movieResponseModel);
        }
        return new ResponseEntity<>(movieList,HttpStatus.OK);
    }
}
