package com.example.finalprogressreview.repositories;

import com.example.finalprogressreview.models.Movie;

import java.util.List;

public class MovieRepositoryImpl implements MovieRepository {
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
    public void create(Movie movie) {

    }

    @Override
    public void update(Movie movie) {

    }

    @Override
    public void delete(int id) {

    }
}
