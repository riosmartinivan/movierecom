package com.example.movierecom.services.impl;

import com.example.movierecom.config.ServiceAPIKeys;
import com.example.movierecom.config.ServiceURLsProperties;
import com.example.movierecom.exceptions.BadRequestException;
import com.example.movierecom.model.Genre;
import com.example.movierecom.model.MovieDTO;
import com.example.movierecom.model.TmdbGenresResponse;
import com.example.movierecom.model.TmdbMoviesResponse;
import com.example.movierecom.services.TmdbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TmdbServiceImpl implements TmdbService {

    private final RestTemplate restTemplate;
    private final ServiceURLsProperties serviceURLsProperties;
    private final ServiceAPIKeys serviceAPIKeys;

    public TmdbServiceImpl(RestTemplate restTemplate,
                           ServiceURLsProperties serviceURLsProperties,
                           ServiceAPIKeys serviceAPIKeys) {
        this.restTemplate = restTemplate;
        this.serviceURLsProperties = serviceURLsProperties;
        this.serviceAPIKeys = serviceAPIKeys;
    }

    @Override
    public List<MovieDTO> getMoviesByGenre(List<String> genreNames, boolean includeAdult) {

        List<Genre> genres = getGenres();
        List<Long> genreIds = getGenreIds(genres, genreNames);
        if (genreIds.isEmpty()) {
            throw new BadRequestException("No valid genres found");
        }

        String genreIdsStr = genreIds.stream().map(String::valueOf).collect(Collectors.joining(","));

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(serviceURLsProperties.getTmdbServiceURL()
                + "/discover/movie")
            .queryParam("api_key", serviceAPIKeys.getTmdbAPIKey())
            .queryParam("sort_by", "popularity.desc")
            .queryParam("include_adult", includeAdult)
            .queryParam("with_genres", genreIdsStr);

        ResponseEntity<TmdbMoviesResponse> response = restTemplate.getForEntity(
            builder.toUriString(), TmdbMoviesResponse.class);

        if (!response.getStatusCode().equals(HttpStatus.OK) || response.getBody() == null) {
            return null;
        }

        List<MovieDTO> result = new ArrayList<>();
        for (MovieDTO m : response.getBody().getResults()) {
            m.setGenres(getGenreNames(genres, m.getGenreIds()));
            m.setGenreIds(null);
            result.add(m);
        }

        return result;
    }

    @Override
    public List<Genre> getGenres() {

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(serviceURLsProperties.getTmdbServiceURL()
                + "/genre/movie/list")
            .queryParam("api_key", serviceAPIKeys.getTmdbAPIKey());

        ResponseEntity<TmdbGenresResponse> response = restTemplate.getForEntity(
            builder.toUriString(), TmdbGenresResponse.class);

        if (!response.getStatusCode().equals(HttpStatus.OK) || response.getBody() == null) {
            throw new BadRequestException("Error getting genres from tmdb");
        }

        return response.getBody().getGenres();
    }


    private List<Long> getGenreIds(List<Genre> genres, List<String> genreNames) {
        return genres.stream().filter(g -> genreNames.contains(g.getName()))
            .map(Genre::getId).collect(Collectors.toList());
    }

    private List<String> getGenreNames(List<Genre> genres, List<Long> genreIds) {
        return genres.stream().filter(g -> genreIds.contains(g.getId()))
            .map(Genre::getName).collect(Collectors.toList());
    }
}
