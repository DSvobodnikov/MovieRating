package com.example.finalprogressreview.services;

import com.example.finalprogressreview.models.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> get();

    Movie get(int id);

    Movie getByTitle(String title);

    Movie create(Movie movie);

    Movie update(Movie movie);

    void delete(int id);



}
