package com.resources;

import com.scraper.Scraper;
import org.junit.Test;

/**
 * Created by J-B Leblanc on 11/01/2018.
 */
public class ScraperTest {

    @Test(expected = IllegalArgumentException.class)
    public void ScrapWithEmptyUrl(){
        Scraper scraper = new Scraper();
        scraper.scrap("");

    }

    @Test(expected = IllegalArgumentException.class)
    public void ScrapWithNonExistingUrl(){
        Scraper scraper = new Scraper();
        scraper.scrap("lol.com");
    }

    @Test
    public void ScrapVerifyTheTypeOfReturn(){
        Scraper scraper = new Scraper();
        scraper.scrap("http://www.lol.com");

    }






}
