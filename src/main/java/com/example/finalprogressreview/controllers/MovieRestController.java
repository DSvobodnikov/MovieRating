package com.example.finalprogressreview.controllers;

import com.example.finalprogressreview.helpers.AuthenticationHelper;
import com.example.finalprogressreview.helpers.MovieMapper;
import com.example.finalprogressreview.models.Movie;
import com.example.finalprogressreview.models.dtos.MovieDto;
import com.example.finalprogressreview.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieRestController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public MovieRestController(MovieService movieService, MovieMapper movieMapper, AuthenticationHelper authenticationHelper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.get().stream().map(movieMapper::to)
    }
}
