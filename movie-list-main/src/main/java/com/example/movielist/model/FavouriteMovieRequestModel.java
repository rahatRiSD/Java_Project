package com.example.movielist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteMovieRequestModel {
    private String title;
    private String cast;
    private String category;
    private String releaseDate;
    private double budget;
}
