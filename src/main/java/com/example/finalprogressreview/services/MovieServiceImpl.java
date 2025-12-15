package com.example.finalprogressreview.services;

import com.example.finalprogressreview.externalApi.OmdbExternalRating;
import com.example.finalprogressreview.models.Movie;
import com.example.finalprogressreview.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final OmdbExternalRating externalRatingClient;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,
                            OmdbExternalRating externalRatingClient) {
        this.movieRepository = movieRepository;
        this.externalRatingClient = externalRatingClient;
    }

    @Override
    public List<Movie> get() {
        return movieRepository.get();
    }

    @Override
    public Movie get(int id) {
        return movieRepository.get(id);
    }

    @Override
    public Movie getByTitle(String title) {
        return movieRepository.getByTitle(title);
    }

    @Override
    public Movie create(Movie movie) {
        movieRepository.create(movie);
        enrichRatingAsync(movie.getId(), movie.getTitle());
        return movie;
    }

    @Override
    public Movie update(Movie movie) {
        movieRepository.update(movie);
        return movie;
    }

    @Override
    public void delete(int id) {
        movieRepository.delete(id);
    }

    @Async
    public void enrichRatingAsync(int movieId, String title) {
        Movie movie = movieRepository.get(movieId);
        Integer rating = externalRatingClient.getRatingByTitle(title);

        if (rating != null) {
                movie.setRating(rating);
                movieRepository.update(movie);
            }else  {
                movie.setRating(0);
                movieRepository.update(movie);
            }
    }
}
