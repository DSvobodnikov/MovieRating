package com.example.finalprogressreview.models.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class MovieDto {


    private int id;

    @NotBlank(message = "Title is required")
    private String title;

    private String director;

    private Date releaseYear;

    private int rating;

    public MovieDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
