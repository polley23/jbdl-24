package com.company;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "movie")
public class Movie {
    @Id
    private Long id;
    @Column(name = "movie_name", nullable = false,length = 1024)
    private String movieName;

    public Movie(final Long id, final String movieName) {
        this.id = id;
        this.movieName = movieName;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }
}
