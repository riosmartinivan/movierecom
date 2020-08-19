package com.example.movierecom.services.impl;

import com.example.movierecom.exceptions.BadRequestException;
import com.example.movierecom.model.Genre;
import com.example.movierecom.model.MovieDTO;
import com.example.movierecom.services.ImdbService;
import com.example.movierecom.services.NetflixService;
import com.example.movierecom.services.RecommendedService;
import com.example.movierecom.services.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendedServiceImpl implements RecommendedService {

    private final TmdbService tmdbService;
    private final NetflixService netflixService;
    private final ImdbService imdbService;

    @Autowired
    public RecommendedServiceImpl(TmdbService tmdbService,
                                  NetflixService netflixService,
                                  ImdbService imdbService) {
        this.tmdbService = tmdbService;
        this.netflixService = netflixService;
        this.imdbService = imdbService;
    }

    @Override
    public List<MovieDTO> getRecommendedMovies(List<String> genreNames, boolean includeAdult) {

        List<MovieDTO> movies = netflixService.getMoviesByGenre(genreNames, includeAdult);  // Trying with netflix
        if (movies == null) {
            movies = imdbService.getMoviesByGenre(genreNames, includeAdult);  // Trying with IMDB
        }
        if (movies == null) {
            movies = tmdbService.getMoviesByGenre(genreNames, includeAdult);  // Trying with TMDB (working)
        }
        if (movies == null) {
            throw new BadRequestException("Error getting recommended movies");
        }

        return movies;
    }

    @Override
    public List<String> getAvailableGenres() {

        List<Genre> genres = netflixService.getGenres();  // Trying with netflix
        if (genres == null) {
            genres = imdbService.getGenres();  // Trying with netflix
        }
        if (genres == null) {
            genres = tmdbService.getGenres();  // Trying with netflix
        }
        if (genres == null) {
            throw new BadRequestException("Error getting the genres");
        }

        return genres.stream().map(Genre::getName).collect(Collectors.toList());
    }
}
