package com.example.finalprogressreview.services;

import com.example.finalprogressreview.models.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImpl implements  MovieService {


    @Override
    public List<Movie> get() {
        return List.of();
    }

    @Override
    public Movie get(int id) {
        return null;
    }

    @Override
    public Movie getByTitle(String title) {
        return null;
    }

    @Override
    public Movie create(Movie movie) {
        return null;
    }

    @Override
    public Movie update(Movie movie) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
