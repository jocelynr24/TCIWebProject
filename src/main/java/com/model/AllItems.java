package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class of the AllItems provided in the crawler.
 * Used by the method getAllItems() in the crawler.
 *
 * @see com.crawler.Crawler#getAllItems(String)
 */

public class AllItems {
    private long time;
    private List<Object> movies = new ArrayList<>();
    private List<Object> books = new ArrayList<>();
    private List<Object> musics = new ArrayList<>();

    public AllItems(long time, List<Object> movies, List<Object> books, List<Object> musics) {
        this.time = time;
        this.movies = movies;
        this.books = books;
        this.musics = musics;
    }

    public AllItems() { }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<Object> getMovies() {
        return movies;
    }

    public void setMovies(List<Object> movies) {
        this.movies = movies;
    }

    public List<Object> getBooks() {
        return books;
    }

    public void setBooks(List<Object> books) {
        this.books = books;
    }

    public List<Object> getMusics() {
        return musics;
    }

    public void setMusics(List<Object> musics) {
        this.musics = musics;
    }

    public void addMovie(Object movie){
        this.movies.add(movie);
    }

    public void addBook(Object book){
        this.books.add(book);
    }

    public void addMusic(Object music){
        this.musics.add(music);
    }
}
