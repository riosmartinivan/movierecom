package com.example.movierecom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {

    @EqualsAndHashCode.Include
    private String originalTitle;

    private String title;
    private Double popularity;
    private String overview;
    private boolean adult;

    @JsonProperty("vote_count")
    private Double voteCount;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("release_date")
    private Date releaseDate;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty(value = "genre_ids", access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> genreIds;


    private List<String> genres;
}