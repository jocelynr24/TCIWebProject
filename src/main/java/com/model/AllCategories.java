package com.model;

import java.util.Arrays;
import java.util.Objects;

public class AllCategories {
    int id;
    long time;
    Book[] books;
    Movie[] movies;
    Music[] music;

    public AllCategories() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
    }

    public Music[] getMusic() {
        return music;
    }

    public void setMusic(Music[] music) {
        this.music = music;
    }

    @Override
    public String toString() {
        return "AllCategories{" +
                "id=" + id +
                ", time=" + time +
                ", books=" + Arrays.toString(books) +
                ", movies=" + Arrays.toString(movies) +
                ", music=" + Arrays.toString(music) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllCategories that = (AllCategories) o;
        return id == that.id &&
                time == that.time &&
                Arrays.equals(books, that.books) &&
                Arrays.equals(movies, that.movies) &&
                Arrays.equals(music, that.music);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, books, movies, music);
    }
}
