package com.example.movierecom.services.impl;

import com.example.movierecom.model.Genre;
import com.example.movierecom.model.MovieDTO;
import com.example.movierecom.services.ImdbService;
import com.example.movierecom.services.NetflixService;
import com.example.movierecom.services.RecommendedService;
import com.example.movierecom.services.TmdbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecommendedServiceImplTest {

    private TmdbService tmdbService;

    private NetflixService netflixService;
    private ImdbService imdbService;

    private RecommendedService recommendedService;

    private final String TITLE = "Test title";
    private final String GENRE_NAME = "Action";

    @BeforeEach
    void setUp() {
        tmdbService = mock(TmdbServiceImpl.class);
        netflixService = new NetflixServiceImpl();
        imdbService = new ImdbServiceImpl();

        recommendedService = new RecommendedServiceImpl(tmdbService,
            netflixService, imdbService);
    }

    @Test
    void getRecommendedMovies() {
        List<MovieDTO> movies = new ArrayList<MovieDTO>() {{
            add(MovieDTO.builder()
                .title(TITLE)
                .build());
        }};
        when(tmdbService.getMoviesByGenre(any(), anyBoolean())).thenReturn(movies);

        List<MovieDTO> response = recommendedService.getRecommendedMovies(any(), anyBoolean());

        assertEquals(response, movies);
    }

    @Test
    void getAvailableGenres() {
        List<Genre> genres = new ArrayList<Genre>() {{
            add(Genre.builder()
                .name(GENRE_NAME)
                .id(28L)
                .build());
        }};
        when(tmdbService.getGenres()).thenReturn(genres);

        List<String> genresStr = recommendedService.getAvailableGenres();

        assertEquals(genresStr.get(0), genres.get(0).getName());
    }
}