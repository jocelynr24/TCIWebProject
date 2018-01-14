package com.scrapper;

import com.scraper.Scraper;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Class containing all the tests made on the Scraper class
 *
 * @author J-B Leblanc
 */
public class ScraperTest {


    /**
     * ScrapWithEmptyUrl : tests the method when the parameter is an empty URL
     */
    @Test(expected = IllegalArgumentException.class)
    public void scrapAnEmptyUrlShouldThrowAnException(){
        Scraper scraper = new Scraper();
        scraper.scrap("");

    }


    /**
     * ScrapWithNonExistingUrl : tests the method when the Url is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void scrapAnInexistingUrlShouldThrowAnException(){
        Scraper scraper = new Scraper();
        scraper.scrap("lol.com");
    }

    /**
     * ScrapReturnBook : Testing what return a well known page containing book information
     */
    @Test
    public void scrapASpecificUrlShouldWork(){
        Scraper scraper = new Scraper();
        String actualResult = scraper.scrap("https://i398507.hera.fhict.nl/tci/details.php?id=102");
        String expectedResult = "{\"Category\":\"Books\",\"Format\":\"Ebook\",\"Year\":\"2008\",\"ISBN\":\"978-0132350884\",\"Authors\":\"Robert C. Martin\",\"Title\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"Genre\":\"Tech\",\"Publisher\":\"Prentice Hall\"}";
        assertEquals(expectedResult, actualResult);
    }


}