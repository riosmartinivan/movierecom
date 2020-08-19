package com.example.movierecom.services;

import com.example.movierecom.model.MovieDTO;

import java.util.List;

public interface RecommendedService {

    List<MovieDTO> getRecommendedMovies(List<String> genreNames, boolean includeAdult);

    List<String> getAvailableGenres();
}
