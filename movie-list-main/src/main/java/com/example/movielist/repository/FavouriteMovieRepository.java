package com.example.movielist.repository;

import com.example.movielist.entity.FavouriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteMovieRepository extends JpaRepository<FavouriteMovie,Integer>{
    void deleteByUserIdAndMovieId(Long userId, Long movieId);
    FavouriteMovie findByUserId(Long userId);
}
