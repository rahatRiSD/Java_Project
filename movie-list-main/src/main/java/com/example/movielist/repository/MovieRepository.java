package com.example.movielist.repository;

import com.example.movielist.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Movie findByTitle(String movieName);
    Movie findByTitleContainingIgnoreCaseOrCastContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrderByTitleAsc(String title,String cast,String category);

}
