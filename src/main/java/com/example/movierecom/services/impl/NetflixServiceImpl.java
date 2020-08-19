package com.example.movierecom.services.impl;

import com.example.movierecom.model.Genre;
import com.example.movierecom.model.MovieDTO;
import com.example.movierecom.services.NetflixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetflixServiceImpl implements NetflixService {

    Logger logger = LoggerFactory.getLogger(NetflixServiceImpl.class);

    @Override
    public List<MovieDTO> getMoviesByGenre(List<String> genreNames, boolean includeAdult) {
        logger.warn("Not implemented - Unknown API");
        logger.warn("Mock needed!");
        return null;
    }

    @Override
    public List<Genre> getGenres() {
        logger.warn("Not implemented - Unknown API");
        logger.warn("Mock needed!");
        return null;
    }
}
