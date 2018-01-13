package com.serializer;

import com.model.Book;
import com.model.Movie;
import com.model.Music;
import org.json.JSONException;
import org.json.JSONObject;

public class CrawlerSerializer {
    public Movie serializeToMovie (JSONObject jsonObject, int ID) {
        String name = jsonObject.getString("Title");
        String genre = jsonObject.getString("Genre");
        String format = jsonObject.getString("Format");
        int year = Integer.parseInt(jsonObject.getString("Year"));
        String director = jsonObject.getString("Director");

        String writersLine = jsonObject.getString("Writers");
        String[] writers = writersLine.split(",");

        String starsLine = jsonObject.getString("Stars");
        String[] stars = starsLine.split(",");

        return new Movie(ID, name, genre, format, year, director, writers, stars);
    }

    public Music serializeToMusic (JSONObject jsonObject, int ID) {
        String name = jsonObject.getString("Title");
        String genre = jsonObject.getString("Genre");
        String format = jsonObject.getString("Format");
        int year = Integer.parseInt(jsonObject.getString("Year"));
        String artist = jsonObject.getString("Artist");
        return new Music(ID, name, genre, format, year, artist);
    }

    public Book serializeToBook (JSONObject jsonObject, int ID) {
        String title = jsonObject.getString("Title");
        String genre = jsonObject.getString("Genre");
        String format = jsonObject.getString("Format");
        int year = Integer.parseInt(jsonObject.getString("Year"));

        String authorsLine = jsonObject.getString("Authors");
        String[] authors = authorsLine.split(",");

        String publisher = jsonObject.getString("Publisher");
        String isbn = jsonObject.getString("ISBN");
        return new Book(ID, title, genre, format, year, authors, publisher, isbn);
    }
}
