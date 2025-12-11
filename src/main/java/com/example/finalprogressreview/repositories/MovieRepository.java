package com.example.finalprogressreview.repositories;

import com.example.finalprogressreview.models.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> get();

    Movie get (int id);

    Movie getByTitle(String title);

    void create (Movie movie);

    void update (Movie movie);

    void delete (int id);

}
