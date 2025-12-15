package com.example.finalprogressreview.controllers;

import com.example.finalprogressreview.exceptions.AuthorizationException;
import com.example.finalprogressreview.helpers.AuthenticationHelper;
import com.example.finalprogressreview.helpers.MovieMapper;
import com.example.finalprogressreview.models.Movie;
import com.example.finalprogressreview.models.User;
import com.example.finalprogressreview.models.dtos.MovieDto;
import com.example.finalprogressreview.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieRestController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public MovieRestController(MovieService movieService,
                               MovieMapper movieMapper,
                               AuthenticationHelper authenticationHelper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.authenticationHelper = authenticationHelper;
    }

    private User authenticate(HttpHeaders headers) {
        return authenticationHelper.tryGetUser(headers);
    }

    private void ensureAdmin(User user) {
        if (!user.isAdmin()) {
            throw new AuthorizationException("Admin privileges required.");
        }
    }

    @GetMapping
    public List<MovieDto> getMovies(@RequestHeader HttpHeaders headers) {
        User user = authenticate(headers);
        return movieService.get()
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public MovieDto getMovie(@RequestHeader HttpHeaders headers,
                             @PathVariable int id) {
        User user = authenticate(headers);
        Movie movie = movieService.get(id);
        return movieMapper.toDto(movie);
    }

    @PostMapping
    public MovieDto createMovie(@RequestHeader HttpHeaders headers,
                                @Valid @RequestBody MovieDto movieDto) {
        User user = authenticate(headers);
        ensureAdmin(user);

        Movie movie = movieMapper.fromDto(movieDto);
        Movie created = movieService.create(movie);
        return movieMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public MovieDto updateMovie(@RequestHeader HttpHeaders headers,
                                @PathVariable int id,
                                @Valid @RequestBody MovieDto movieDto) {
        User user = authenticate(headers);
        ensureAdmin(user);

        Movie movie = movieMapper.fromDto(movieDto);
        movie.setId(id);
        Movie updated = movieService.update(movie);
        return movieMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@RequestHeader HttpHeaders headers,
                            @PathVariable int id) {
        User user = authenticate(headers);
        ensureAdmin(user);

        movieService.delete(id);
    }
}