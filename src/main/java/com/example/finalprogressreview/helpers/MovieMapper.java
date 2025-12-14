package com.example.finalprogressreview.helpers;

import com.example.finalprogressreview.models.Movie;
import com.example.finalprogressreview.models.dtos.MovieDto;
import org.springframework.stereotype.Component;


@Component
public class MovieMapper{
    public Movie fromDto(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDirector(movieDto.getDirector());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setRating(movieDto.getRating());
        return movie;
    }

    public MovieDto from(Movie movie) {}
}
