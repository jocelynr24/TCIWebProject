package com.model;

import java.util.Objects;

public class Music {
    String genre;
    String format;
    int year;
    String artist;

    public Music(String genre, String format, int year, String artist) {
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.artist = artist;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return year == music.year &&
                Objects.equals(genre, music.genre) &&
                Objects.equals(format, music.format) &&
                Objects.equals(artist, music.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, format, year, artist);
    }
}
