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
}
