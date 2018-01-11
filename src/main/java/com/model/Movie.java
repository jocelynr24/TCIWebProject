package com.model;

import java.util.Arrays;
import java.util.Objects;

public class Movie {

    String name;
    String genre;
    String format;
    int year;
    String director;
    String [] writer;
    String [] stars;

    public Movie(String name, String genre, String format, int year, String director, String[] writer, String[] stars) {
        this.name = name;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.director = director;
        this.writer = writer;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getWriter() {
        return writer;
    }

    public void setWriter(String[] writer) {
        this.writer = writer;
    }

    public String[] getStars() {
        return stars;
    }

    public void setStars(String[] stars) {
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year &&
                Objects.equals(name, movie.name) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(format, movie.format) &&
                Objects.equals(director, movie.director) &&
                Arrays.equals(writer, movie.writer) &&
                Arrays.equals(stars, movie.stars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, format, year, director, writer, stars);
    }
}
