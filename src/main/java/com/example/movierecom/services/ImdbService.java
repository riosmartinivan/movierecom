package com.example.movierecom.services;

import com.example.movierecom.model.Genre;
import com.example.movierecom.model.MovieDTO;

import java.util.List;

public interface ImdbService {

    List<MovieDTO> getMoviesByGenre(List<String> genreNames, boolean includeAdult);
    List<Genre> getGenres();
}
