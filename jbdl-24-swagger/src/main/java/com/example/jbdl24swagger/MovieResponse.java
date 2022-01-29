package com.example.jbdl24swagger;


public class MovieResponse {
    String name;
    Integer rating;

    public MovieResponse(final String name, final Integer rating) {
        this.name = name;
        this.rating = rating;
    }

    public MovieResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(final Integer rating) {
        this.rating = rating;
    }
}
