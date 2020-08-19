package com.example.movierecom.services.impl;

import com.example.movierecom.config.ServiceAPIKeys;
import com.example.movierecom.config.ServiceURLsProperties;
import com.example.movierecom.model.Genre;
import com.example.movierecom.model.MovieDTO;
import com.example.movierecom.model.TmdbGenresResponse;
import com.example.movierecom.model.TmdbMoviesResponse;
import com.example.movierecom.services.TmdbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TmdbServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    private TmdbService tmdbService;

    private final String TITLE = "Test title";
    private final String GENRE_NAME = "Action";
    private final Long GENRE_ID = 28L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ServiceURLsProperties serviceURLsProperties = mock(ServiceURLsProperties.class);
        ServiceAPIKeys serviceAPIKeys = mock(ServiceAPIKeys.class);

        when(serviceURLsProperties.getTmdbServiceURL()).thenReturn("http://testUrl");
        when(serviceAPIKeys.getTmdbAPIKey()).thenReturn("testKey");

        tmdbService = new TmdbServiceImpl(restTemplate,
            serviceURLsProperties, serviceAPIKeys);
    }

    @Test
    void getMoviesByGenre() {
        List<MovieDTO> moviesResponse = new ArrayList<MovieDTO>() {{
            add(MovieDTO.builder()
                .title(TITLE)
                .genres(new ArrayList<String>() {{add(GENRE_NAME);}})
                .build());
        }};
        List<MovieDTO> movies = new ArrayList<MovieDTO>() {{
            add(MovieDTO.builder()
                .title(TITLE)
                .genreIds(new ArrayList<Long>() {{add(GENRE_ID);}})
                .build());
        }};
        TmdbMoviesResponse tmdbMoviesResponse = TmdbMoviesResponse.builder()
            .page(1)
            .results(movies)
            .totalPages(500)
            .totalResults(10000)
            .build();
        ResponseEntity<TmdbMoviesResponse> tmdbMoviesResponseE =
            new ResponseEntity<>(tmdbMoviesResponse, HttpStatus.OK);

        List<Genre> genres = new ArrayList<Genre>() {{
            add(Genre.builder()
                .name(GENRE_NAME)
                .id(GENRE_ID)
                .build());
        }};
        TmdbGenresResponse tmdbGenresResponse = TmdbGenresResponse.builder()
            .genres(genres)
            .build();
        ResponseEntity<TmdbGenresResponse> tmdbGenresResponseE =
            new ResponseEntity<>(tmdbGenresResponse, HttpStatus.OK);

        doReturn(tmdbGenresResponseE).when(restTemplate)
            .getForEntity(anyString(), eq(TmdbGenresResponse.class));
        doReturn(tmdbMoviesResponseE).when(restTemplate)
            .getForEntity(anyString(), eq(TmdbMoviesResponse.class));


        List<MovieDTO> response = tmdbService.getMoviesByGenre(
            new ArrayList<String>() {{add(GENRE_NAME);}},
            true);

        assertEquals(response, moviesResponse);
    }
}