package com.model;

public class OnePerItem {
    private long time;
    private Object movie;
    private Object book;
    private Object music;

    public OnePerItem(long time, Object movie, Object book, Object music) {
        this.time = time;
        this.movie = movie;
        this.book = book;
        this.music = music;
    }

    public OnePerItem() { }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Object getMovie() {
        return movie;
    }

    public void setMovie(Object movie) {
        this.movie = movie;
    }

    public Object getBook() {
        return book;
    }

    public void setBook(Object book) {
        this.book = book;
    }

    public Object getMusic() {
        return music;
    }

    public void setMusic(Object music) {
        this.music = music;
    }
}
