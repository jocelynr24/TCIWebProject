package com.serializer;

import com.model.Book;
import com.model.Movie;
import com.model.Music;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class used to serialize objects received from the scraper to the crawler.
 *
 * @author Jocelyn Routin
 * @author Adrien Cosson
 */

public class CrawlerSerializer {

    /**
     * This method serializes a JSON object into a Movie object.
     * @param jsonObject The JSON object we want to serialize.
     * @param ID The ID of the movie.
     * @return A Movie object containing the information of the JSON object.
     */
    public Movie serializeToMovie (JSONObject jsonObject, int ID) {
        try{
            if(ID >= 0){
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
            } else {
                throw new IllegalArgumentException("ID must be positive.");
            }
        } catch(JSONException e){
            throw new JSONException("JSON is incorrect");
        }
    }

    /**
     * This method serializes a JSON object into a Music object.
     * @param jsonObject The JSON object we want to serialize.
     * @param ID The ID of the music.
     * @return A Music object containing the information of the JSON object.
     */
    public Music serializeToMusic (JSONObject jsonObject, int ID) {
        try{
            if(ID >= 0){
                String name = jsonObject.getString("Title");
                String genre = jsonObject.getString("Genre");
                String format = jsonObject.getString("Format");
                int year = Integer.parseInt(jsonObject.getString("Year"));
                String artist = jsonObject.getString("Artist");
                return new Music(ID, name, genre, format, year, artist);
            } else {
                throw new IllegalArgumentException("ID must be positive.");
            }
        } catch(JSONException e){
            throw new JSONException("JSON is incorrect");
        }
    }

    /**
     * This method serializes a JSON object into a Book object.
     * @param jsonObject The JSON object we want to serialize.
     * @param ID The ID of the book.
     * @return A Book object containing the information of the JSON object.
     */
    public Book serializeToBook (JSONObject jsonObject, int ID) {
        try{
            if(ID >= 0){
                String title = jsonObject.getString("Title");
                String genre = jsonObject.getString("Genre");
                String format = jsonObject.getString("Format");
                int year = Integer.parseInt(jsonObject.getString("Year"));
                String authorsLine = jsonObject.getString("Authors");
                String[] authors = authorsLine.split(",");
                String publisher = jsonObject.getString("Publisher");
                String isbn = jsonObject.getString("ISBN");
                return new Book(ID, title, genre, format, year, authors, publisher, isbn);
            } else {
                throw new IllegalArgumentException("ID must be positive.");
            }
        } catch(JSONException e){
            throw new JSONException("JSON is incorrect");
        }
    }
}
