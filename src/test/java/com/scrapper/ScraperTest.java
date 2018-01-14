package com.scrapper;

import com.scraper.Scraper;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Class containing all the tests made on the Scraper class.
 *
 * @author J-B Leblanc
 */
public class ScraperTest {
    private Scraper scraper;

    /**
     * Setup of the test class.
     */
    @Before
    public void setup(){
        scraper = new Scraper();
    }

    /**
     * scrapAnEmptyUrlShouldThrowAnException : tests the method when the parameter is an empty URL.
     *
     * @see com.scraper.Scraper#scrap(String)
     */
    @Test(expected = IllegalArgumentException.class)
    public void scrapAnEmptyUrlShouldThrowAnException(){
        scraper.scrap("");
    }

    /**
     * scrapAnUnexistingUrlShouldThrowAnException : tests the method when the Url is not valid.
     *
     * @see com.scraper.Scraper#scrap(String)
     */
    @Test(expected = IllegalArgumentException.class)
    public void scrapAnUnexistingUrlShouldThrowAnException(){
        scraper.scrap("website.com");
    }

    /**
     * scrapASpecificUrlShouldWork : Testing what return a well known page containing book information.
     *
     * @see com.scraper.Scraper#scrap(String)
     */
    @Test
    public void scrapASpecificUrlShouldWork(){
        String actualResult = scraper.scrap("https://i398507.hera.fhict.nl/tci/details.php?id=102");
        String expectedResult = "{\"Category\":\"Books\",\"Format\":\"Ebook\",\"Year\":\"2008\",\"ISBN\":\"978-0132350884\",\"Authors\":\"Robert C. Martin\",\"Title\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"Genre\":\"Tech\",\"Publisher\":\"Prentice Hall\"}";
        assertEquals(expectedResult, actualResult);
    }
}