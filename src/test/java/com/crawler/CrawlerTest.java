package com.crawler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Class containing all the tests made on the web crawler class.
 *
 * @author Jocelyn Routin
 * @author Adrien Cosson
 */

public class CrawlerTest {
    Crawler crawler;

    /**
     * Setup of the test class.
     */
    @Before
    public void setup(){
        // We create a new web crawler object for all the tests
        crawler = new Crawler();
    }

    /**
     * In the case the getPageLinks() method succeeds, it returns a "true" boolean.
     * We are testing here if we get this boolean correctly.
     */
    @Test
    public void getPageLinksSucceeds(){
        // Act (we get the value of the boolean by giving a valid URL)
        boolean acknowledgement = crawler.getPageLinks("https://i398507.hera.fhict.nl/tci/details.php?id=202", "https://i398507.hera.fhict.nl");
        // Assert (the boolean is "true")
        Assert.assertEquals(true, acknowledgement);
    }

    /**
     * In the case we give a wrong URL to the getPageLinks() method, it returns an IllegalArgumentException exception.
     * We are testing here if we get this exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void getPageLinksHasAnInvalidURL(){
        // Act (we give a wrong URL, it should throw an exception)
        crawler.getPageLinks("Invalid URL", "");
    }
	
	/**
     * The webCrawlerShouldThrowExceptionForWrongUrl() method should throw an exception if we give a wrong URL format.
     * We are testing here if we get this exception.
     */
    @Test (expected = IllegalArgumentException.class)
    public void webCrawlerShouldThrowExceptionForWrongUrl() {
        // Act (Giving a wrong URL to the basicWebCrawler, it should throw an exception)
        crawler.webCrawler("Worng URL");
    }

    /**
     * The getSpecificItemReturnsAString() method should always return a string result.
     * We are testing here if this statement is true.
     */
    @Test
    public void getSpecificItemReturnsAString(){
        // Act (we give a URL and a name to find something)
        String specificItem = crawler.getSpecificItem("https://i398507.hera.fhict.nl/tci/", "Office Space");
        // Assert (that the previously returned item is a string)
        Assert.assertThat(specificItem, instanceOf(String.class));
    }

    /**
     * The getSpecificItemReturnsAString() method should return a JSON string when we give a URL and a name.
     * We are testing here if the JSON returned by the crawler is the same JSON file that we expect (for example, with "Office Space").
     */
    @Test
    public void getSpecificItemReturnsValueForOfficeSpace(){
        // Arrange (we define the expected JSON string)
        String expectedJSON = "{\"result\":{\"director\":\"Mike Judge\",\"format\":\"Blu-ray\",\"genre\":\"Comedy\",\"id\":202,\"name\":\"Office Space\",\"stars\":[\"Ron Livingston\",\" Jennifer Aniston\",\" David Herman\",\" Ajay Naidu\",\" Diedrich Bader\",\" Stephen Root\"],\"writers\":[\"William Goldman\"],\"year\":1999}}";
        // Act
        // We crawl the website to find the item
        String specificItem = crawler.getSpecificItem("https://i398507.hera.fhict.nl/tci/", "Office Space");
        // We remove the time value on the JSON which will be always different
        specificItem = specificItem.replaceAll(",\"time\":\\d{1,20}", "");
        // Assert (that the expected JSON is the same than the returned JSON)
        Assert.assertEquals(expectedJSON, specificItem);
    }

    /**
     * The getSpecificItemReturnsAString() method should throw an exception if we give a wrong URL format.
     * We are testing here if we get this exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSpecificItemHasAnInvalidURL(){
        // Act (we give a wrong URL, it should throw an exception)
        crawler.getSpecificItem("Invalid URL", "Office Space");
    }

    /**
     * The getSpecificItemReturnsAString() method should return an empty string if we give a name which is not on the website.
     * We are testing here if we get an empty result
     */
    @Test
    public void getSpecificItemFoundNothing(){
        // Act (we crawl the website to find our not existing element)
        String specificItem = crawler.getSpecificItem("https://i398507.hera.fhict.nl/tci/", "This item does not exist");
        // Assert (that the result is an empty string)
        Assert.assertEquals("", specificItem);
    }
	
	/**
     * The getOnePerItemShouldThrowExceptionForWrongUrl() method should throw an exception if we give a wrong URL format.
     * We are testing here if we get this exception.
     */
    @Test (expected = IllegalArgumentException.class)
    public void getAllItemsShouldThrowExceptionForWrongUrl() {
        // Act (Giving a wrong URL to the basicWebCrawler, it should throw an exception)
        crawler.getAllItems("Wrong URL");
    }

    /**
     * The getOnePerItemShouldReturnAString() method should always return a string result, whether it is empty or filled with information.
     * We are testing here if this statement is true.
     */
    @Test
    public void getAllItemsShouldReturnAString(){
        // Act (we give a URL and a name to find something)
        String onePerItem = crawler.getAllItems("https://i398507.hera.fhict.nl/tci/");
        // Assert (that the previously returned item is a string)
        Assert.assertThat(onePerItem, instanceOf(String.class));
    }

    /**
     * The getOnePerItemShouldReturnValueForSpecificUrl() method tests the fact that getAllItems() method should return the same result for the same parameter.
     */
    @Test
    public void getAllItemsShouldReturnValueForSpecificUrl() {
        // onePerItem contains the result of calling the getAllItems method, with the URL of the index
        String onePerItem = crawler.getAllItems("https://i398507.hera.fhict.nl/tci/");
        // We remove from it the time elapsed which will neve the the same
        onePerItem = onePerItem.replaceAll(",\"time\":\\d{1,20}", "");
        // We store the expected result, but we remove the time which will never be the same, as well as above.
        String expectedResult = "{\"books\":[{\"authors\":[\"Robert C. Martin\"],\"format\":\"Ebook\",\"genre\":\"Tech\",\"id\":102,\"isbn\":\"978-0132350884\",\"publisher\":\"Prentice Hall\",\"title\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"year\":2008},{\"authors\":[\"Robert C. Martin\"],\"format\":\"Audio\",\"genre\":\"Tech\",\"id\":104,\"isbn\":\"007-6092046981\",\"publisher\":\"Prentice Hall\",\"title\":\"The Clean Coder: A Code of Conduct for Professional Programmers\",\"year\":2011},{\"authors\":[\"Erich Gamma\",\" Richard Helm\",\" Ralph Johnson\",\" John Vlissides\"],\"format\":\"Paperback\",\"genre\":\"Tech\",\"id\":101,\"isbn\":\"978-0201633610\",\"publisher\":\"Prentice Hall\",\"title\":\"A Design Patterns: Elements of Reusable Object-Oriented Software\",\"year\":1994},{\"authors\":[\"Martin Fowler\",\" Kent Beck\",\" John Brant\",\" William Opdyke\",\" Don Roberts\"],\"format\":\"Hardcover\",\"genre\":\"Tech\",\"id\":103,\"isbn\":\"978-0201485677\",\"publisher\":\"Addison-Wesley Professional\",\"title\":\"Refactoring: Improving the Design of Existing Code\",\"year\":1999}],\"movies\":[{\"director\":\"Robert Zemeckis\",\"format\":\"DVD\",\"genre\":\"Drama\",\"id\":201,\"name\":\"Forrest Gump\",\"stars\":[\"Tom Hanks\",\" Rebecca Williams\",\" Sally Field\",\" Michael Conner Humphreys\"],\"writers\":[\"Winston Groom\",\" Eric Roth\"],\"year\":1994},{\"director\":\"Peter Jackson\",\"format\":\"Blu-ray\",\"genre\":\"Drama\",\"id\":203,\"name\":\"The Lord of the Rings: The Fellowship of the Ring\",\"stars\":[\"Ron Livingston\",\" Jennifer Aniston\",\" David Herman\",\" Ajay Naidu\",\" Diedrich Bader\",\" Stephen Root\"],\"writers\":[\"J.R.R. Tolkien\",\" Fran Walsh\",\" Philippa Boyens\",\" Peter Jackson\"],\"year\":2001},{\"director\":\"Mike Judge\",\"format\":\"Blu-ray\",\"genre\":\"Comedy\",\"id\":202,\"name\":\"Office Space\",\"stars\":[\"Ron Livingston\",\" Jennifer Aniston\",\" David Herman\",\" Ajay Naidu\",\" Diedrich Bader\",\" Stephen Root\"],\"writers\":[\"William Goldman\"],\"year\":1999},{\"director\":\"Rob Reiner\",\"format\":\"DVD\",\"genre\":\"Comedy\",\"id\":204,\"name\":\"The Princess Bride\",\"stars\":[\"Cary Elwes\",\" Mandy Patinkin\",\" Robin Wright\",\" Chris Sarandon\",\" Christopher Guest\",\" Wallace Shawn\",\" AndrÃ© the Giant\",\" Fred Savage\",\" Peter Falk\",\" Billy Crystal\"],\"writers\":[\"William Goldman\"],\"year\":1987}],\"musics\":[{\"artist\":\"Ludwig van Beethoven\",\"format\":\"CD\",\"genre\":\"Clasical\",\"id\":301,\"name\":\"Beethoven: Complete Symphonies\",\"year\":2012},{\"artist\":\"Elvis Presley\",\"format\":\"Vinyl\",\"genre\":\"Rock\",\"id\":302,\"name\":\"Elvis Forever\",\"year\":2015},{\"artist\":\"Garth Brooks\",\"format\":\"Cassette\",\"genre\":\"Country\",\"id\":303,\"name\":\"No Fences\",\"year\":1990},{\"artist\":\"Nat King Cole\",\"format\":\"MP3\",\"genre\":\"Jaz\",\"id\":304,\"name\":\"The Very Thought of You\",\"year\":2008}]}";
        expectedResult = expectedResult.replaceAll(",\"time\":\\d{1,20}", "");

        // Assert (that the expected JSON is the same than the returned JSON)
        Assert.assertEquals(expectedResult, onePerItem);
    }
}