package com.scrapper;

import com.scraper.Scraper;
import org.junit.Test;

/**
 * Created by J-B Leblanc on 11/01/2018.
 */
public class ScraperTest {


    /**
     * ScrapWithEmptyUrl : tests the method when the parameter is an empty URL
     */
    @Test(expected = IllegalArgumentException.class)
    public void ScrapWithEmptyUrl(){
        Scraper scraper = new Scraper();
        scraper.scrap("");

    }


    /**
     * ScrapWithNonExistingUrl : tests the method when the Url is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void ScrapWithNonExistingUrl(){
        Scraper scraper = new Scraper();
        scraper.scrap("lol.com");
    }


    /**
     * ScrapVerifyTheTypeOfReturn : tests the type of return of the method scraper
     */
    @Test
    public void ScrapVerifyTheTypeOfReturn(){
        Scraper scraper = new Scraper();
        scraper.scrap("http://www.lol.com");

    }

}