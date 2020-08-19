package com.example.movierecom.controllers;

import com.example.movierecom.model.MovieDTO;
import com.example.movierecom.services.RecommendedService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommended")
public class RecommendedController {

    private final RecommendedService recommendedService;

    public RecommendedController(RecommendedService recommendedService) {
        this.recommendedService = recommendedService;
    }

    @GetMapping(value = "/getRecommended", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<MovieDTO>> getRecommended(@RequestParam("genreNames") List<String> genreNames,
                                                         @RequestParam("includeAdult") boolean includeAdult) {

        return ResponseEntity.ok(recommendedService.getRecommendedMovies(genreNames, includeAdult));
    }

    @GetMapping(value = "/getAvailableGenres", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<String>> getAvailableGenres() {

        return ResponseEntity.ok(recommendedService.getAvailableGenres());
    }
}
