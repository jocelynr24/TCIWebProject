package com.model;

public class Book {
    String name;
    String genre;
    String format;
    int year;
    String authors;
    String publisher;
    int isbn;

    public Book(String name, String genre, String format, int year, String authors, String publisher, int isbn) {
        this.name = name;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getFormat() { return format; }

    public void setFormat(String format) { this.format = format; }

    public int getYear() { return year; }

    public String getAuthors() { return authors; }

    public void setAuthors(String authors) { this.authors = authors; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getIsbn() { return isbn; }

    public void setIsbn(int isbn) { this.isbn = isbn; }

    @Override
    public boolean equals(final Object obj)
    {
        if ( obj == null || obj == this || !(obj instanceof Book) )
            return false;

        Book otherBook = (Book) obj;

        if (!otherBook.authors.equals(this.authors))       return false;
        if (!otherBook.format.equals(this.format))     return false;
        if (!otherBook.genre.equals(this.genre)) return false;
        if (!otherBook.name.equals(this.name))   return false;
        if (!otherBook.publisher.equals(this.publisher)) return false;
        if (otherBook.isbn!=this.isbn) return false;
        if (otherBook.year!=this.year) return false;

        return true;
    }
}
