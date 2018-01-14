package com.serializer;

import com.model.Book;
import com.model.Movie;
import com.model.Music;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Class containing all the tests made on the web crawler serializer class.
 * To test the content of the objects we have and we expect, we use the AssertJ library as this one is able to compare the content of objects containing objects.
 * Indeed, as two objects will be different, we only need to test the contents of the objects.
 *
 * @author Jocelyn Routin
 */

public class CrawlerSerializerTest {
    private CrawlerSerializer crawlerSerializer;

    /**
     * Setup of the test class.
     */
    @Before
    public void setup(){
        // We create a new web crawler serializer object for all the tests
        crawlerSerializer = new CrawlerSerializer();
    }

    /**
     * The serializeToMovieShouldReturnAMovieObject() method should return a Movie object.
     * We are testing here if this statement is true.
     */
    @Test
    public void serializeToMovieShouldReturnAMovieObject(){
        // Arrange (we create an example of a valid movie JSON)
        String jsonToSerialize = "{\"Category\":\"Movies\",\"Format\":\"Blu-ray\",\"Year\":\"1999\",\"Writers\":\"William Goldman\",\"Stars\":\"Ron Livingston, Jennifer Aniston, David Herman, Ajay Naidu, Diedrich Bader, Stephen Root\",\"Director\":\"Mike Judge\",\"Title\":\"Office Space\",\"Genre\":\"Comedy\"}";
        JSONObject jsonObject = new JSONObject(jsonToSerialize);
        // Act (we serialize the JSON object)
        Movie returnedMovie = crawlerSerializer.serializeToMovie(jsonObject, 202);
        // Assert (we check the object is a movie)
        Assert.assertThat(returnedMovie, instanceOf(Movie.class));
    }

    /**
     * The serializeToMovieShouldReturnInformationAboutOfficeSpace() method should return information about Office Space movie.
     * We are testing here if this statement is true.
     */
    @Test
    public void serializeToMovieShouldReturnInformationAboutOfficeSpace(){
        // Arrange (the movie from the JSON and a Movie object with the same values)
        String jsonToSerialize = "{\"Category\":\"Movies\",\"Format\":\"Blu-ray\",\"Year\":\"1999\",\"Writers\":\"William Goldman\",\"Stars\":\"Ron Livingston, Jennifer Aniston, David Herman, Ajay Naidu, Diedrich Bader, Stephen Root\",\"Director\":\"Mike Judge\",\"Title\":\"Office Space\",\"Genre\":\"Comedy\"}";
        JSONObject jsonObject = new JSONObject(jsonToSerialize);
        Movie expectedMovie = new Movie(202, "Office Space", "Comedy", "Blu-ray", 1999, "Mike Judge", new String[]{"William Goldman"}, new String[]{"Ron Livingston", " Jennifer Aniston", " David Herman", " Ajay Naidu", " Diedrich Bader", " Stephen Root"});
        // Act (serialize the movie from the JSON into a Movie object)
        Movie actualMovie = crawlerSerializer.serializeToMovie(jsonObject, 202);
        // Assert (that the actualMovie and the expectedMovie have the same values)
        Assertions.assertThat(actualMovie).isEqualToComparingFieldByFieldRecursively(expectedMovie);
    }

    /**
     * The serializeToMovieShouldThrowAnExceptionWhenJsonIsIncorrect() method should return a JSONException when the JSON is not correct.
     * We are testing here if this statement is true.
     */
    @Test(expected = JSONException.class)
    public void serializeToMovieShouldThrowAnExceptionWhenJsonIsIncorrect(){
        // Arrange (we create an invalid JSON object)
        String jsonToSerialize = "Incorrect JSON";
        JSONObject jsonObject = new JSONObject(jsonToSerialize);
        // Act (we call the serialize method)
        Movie returnedMovie = crawlerSerializer.serializeToMovie(jsonObject, 0);
    }

    /**
     * The serializeToMusicShouldReturnAMusicObject() method should return a Music object.
     * We are testing here if this statement is true.
     */
    @Test
    public void serializeToMusicShouldReturnAMusicObject(){
        // Arrange (we create an example of a valid music JSON)
        String jsonToSerialize = "{\"Artist\":\"Ludwig van Beethoven\",\"Category\":\"Music\",\"Format\":\"CD\",\"Year\":\"2012\",\"Title\":\"Beethoven: Complete Symphonies\",\"Genre\":\"Clasical\"}";
        JSONObject jsonObject = new JSONObject(jsonToSerialize);
        // Act (we serialize the JSON object)
        Music returnedMusic = crawlerSerializer.serializeToMusic(jsonObject, 301);
        // Assert (we check the object is a music)
        Assert.assertThat(returnedMusic, instanceOf(Music.class));
    }

    /**
     * The serializeToMovieShouldReturnInformationAboutOfficeSpace() method should return information about Beethoven music.
     * We are testing here if this statement is true.
     */
    @Test
    public void serializeToMusicShouldReturnInformationAboutBeethoven(){
        // Arrange (the music from the JSON and a Music object with the same values)
        String jsonToSerialize = "{\"Artist\":\"Ludwig van Beethoven\",\"Category\":\"Music\",\"Format\":\"CD\",\"Year\":\"2012\",\"Title\":\"Beethoven: Complete Symphonies\",\"Genre\":\"Clasical\"}";
        JSONObject jsonObject = new JSONObject(jsonToSerialize);
        Music expectedMusic = new Music(301, "Beethoven: Complete Symphonies", "Clasical", "CD", 2012, "Ludwig van Beethoven");
        // Act (serialize the music from the JSON into a Music object)
        Music actualMusic = crawlerSerializer.serializeToMusic(jsonObject, 301);
        // Assert (that the actualMusic and the expectedMusic have the same values)
        Assertions.assertThat(actualMusic).isEqualToComparingFieldByFieldRecursively(expectedMusic);
    }

    /**
     * The serializeToMusicShouldThrowAnExceptionWhenJsonIsIncorrect() method should return a JSONException when the JSON is not correct.
     * We are testing here if this statement is true.
     */
    @Test(expected = JSONException.class)
    public void serializeToMusicShouldThrowAnExceptionWhenJsonIsIncorrect(){
        // Arrange (we create an invalid JSON object)
        String jsonToSerialize = "Incorrect JSON";
        JSONObject jsonObject = new JSONObject(jsonToSerialize);
        // Act (we call the serialize method)
        Music returnedMusic = crawlerSerializer.serializeToMusic(jsonObject, 0);
    }

    /**
     * The serializeToBookShouldReturnABookObject() method should return a Book object.
     * We are testing here if this statement is true.
     */
    @Test
    public void serializeToBookShouldReturnABookObject(){
        // Arrange (we create an example of a valid book JSON)
        String jsonToSerialize = "{\"Category\":\"Books\",\"Format\":\"Ebook\",\"Year\":\"2008\",\"ISBN\":\"978-0132350884\",\"Authors\":\"Robert C. Martin\",\"Title\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"Genre\":\"Tech\",\"Publisher\":\"Prentice Hall\"}";
        JSONObject jsonObject = new JSONObject(jsonToSerialize);
        // Act (we serialize the JSON object)
        Book returnedBook = crawlerSerializer.serializeToBook(jsonObject, 102);
        // Assert (we check the object is a book)
        Assert.assertThat(returnedBook, instanceOf(Book.class));
    }

    /**
     * The serializeToMovieShouldReturnInformationAboutOfficeSpace() method should return information about Agile Software book.
     * We are testing here if this statement is true.
     */
    @Test
    public void serializeToBookShouldReturnInformationAboutAgileSoftware(){
        // Arrange (the book from the JSON and a Book object with the same values)
        String jsonToSerialize = "{\"Category\":\"Books\",\"Format\":\"Ebook\",\"Year\":\"2008\",\"ISBN\":\"978-0132350884\",\"Authors\":\"Robert C. Martin\",\"Title\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"Genre\":\"Tech\",\"Publisher\":\"Prentice Hall\"}";
        JSONObject jsonObject = new JSONObject(jsonToSerialize);
        Book expectedBook = new Book(102, "Clean Code: A Handbook of Agile Software Craftsmanship", "Tech", "Ebook", 2008, new String[]{"Robert C. Martin"}, "Prentice Hall", "978-0132350884");
        // Act (serialize the book from the JSON into a Book object)
        Book actualBook = crawlerSerializer.serializeToBook(jsonObject, 102);
        // Assert (that the actualMusic and the expectedBook have the same values)
        Assertions.assertThat(actualBook).isEqualToComparingFieldByFieldRecursively(expectedBook);
    }

    /**
     * The serializeToBookShouldThrowAnExceptionWhenJsonIsIncorrect() method should return a JSONException when the JSON is not correct.
     * We are testing here if this statement is true.
     */
    @Test(expected = JSONException.class)
    public void serializeToBookShouldThrowAnExceptionWhenJsonIsIncorrect(){
        // Arrange (we create an invalid JSON object)
        String jsonToSerialize = "Incorrect JSON";
        JSONObject jsonObject = new JSONObject(jsonToSerialize);
        // Act (we call the serialize method)
        Book returnedBook = crawlerSerializer.serializeToBook(jsonObject, 0);
    }
}