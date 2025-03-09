package com.example.movielist.service.Impl;

import com.example.movielist.entity.FavouriteMovie;
import com.example.movielist.entity.Movie;
import com.example.movielist.model.FavouriteMovieResponseModel;
import com.example.movielist.repository.FavouriteMovieRepository;
import com.example.movielist.repository.MovieRepository;
import com.example.movielist.service.FavouriteMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteMovieServiceImpl implements FavouriteMovieService {
    @Autowired
    private final FavouriteMovieRepository favouriteMovieRepository;
    private final MovieRepository movieRepository;

    @Override
    public void addFavoriteMovie(Long userId, Long movieId) {
        FavouriteMovie favouriteMovie = new FavouriteMovie();
        favouriteMovie.setUserId(userId);
        favouriteMovie.setMovieId(movieId);

    }

    @Override
    public void removeFavoriteMovie(Long userId, Long movieId) {
        favouriteMovieRepository.deleteByUserIdAndMovieId(userId, movieId);
    }

    @Override
    public ResponseEntity<Object> getUserFavoriteMovies(Long userId) {
        List<FavouriteMovie> favoriteMovies = (List<FavouriteMovie>) favouriteMovieRepository.findByUserId(userId);
        List<FavouriteMovieResponseModel> response = new ArrayList<>();

        for (FavouriteMovie favorite : favoriteMovies) {
            Movie movie = movieRepository.findById(favorite.getMovieId()).orElse(null);
            if (movie != null) {
                FavouriteMovieResponseModel movieResponseModel = new FavouriteMovieResponseModel();
                movieResponseModel.setTitle(movie.getTitle());
                movieResponseModel.setCategory(movie.getCategory());
                movieResponseModel.setBudget(movie.getBudget());
                movieResponseModel.setReleaseDate(movie.getReleaseDate());
                response.add(movieResponseModel);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
