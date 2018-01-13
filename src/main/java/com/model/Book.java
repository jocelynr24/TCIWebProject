package com.model;

/**
 * Model class of the book objects we can find on the website.
 */

public class Book {
    private int id;
    private String title;
    private String genre;
    private String format;
    private int year;
    private String[] authors;
    private String publisher;
    private String isbn;

    public Book(int id, String title, String genre, String format, int year, String[] authors, String publisher, String isbn) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
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

    public void setTitle(String title) {
        this.title = title;
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

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
