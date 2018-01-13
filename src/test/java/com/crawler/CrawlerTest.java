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
        basicWebCrawler.webCrawler("Worng URL");
    }
}
