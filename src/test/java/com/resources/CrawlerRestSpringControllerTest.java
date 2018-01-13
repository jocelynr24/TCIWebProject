package com.resources;

import com.crawler.Crawler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class CrawlerRestSpringControllerTest {
    /**
     * Mock the crawler
     */
    @Mock
    private Crawler crawlerMock;

    /**
     * Inject the mock
     */
    @InjectMocks
    private CrawlerRestSpringController controller;


    /**
     * Setup the crawler returns  for the futur tests
     */
    @Before
    public void initMocks() {

        MockitoAnnotations.initMocks(this);
        when(crawlerMock.getSpecificItem("x", "y")).thenReturn("Légende");
        when(crawlerMock.getAllItems("x")).thenReturn("Korra");
    }

    /**
     * Test of searchSpecificItem
     * We need to test a string return
     */
    @Test
    public void testSpecificItem() {
        //assertTrue(controller.searchSpecificItem("x").equals("Légende"));
    }

    /**
     * Test of searchSpecificItem
     * This is a stub method
     * We replace the used functions and put the attended result
     * We need to test a string return
     */
    @Test
    //Stub
    public void testInformation()  {

        controller = new CrawlerRestSpringController();

        controller.setCrawler(new Crawler() {
            public String information() {
                return "de";
            }
        });
        //assertTrue(controller.information().equals("de"));
    }


    /**
     * Test of findFirstMovieBookMusic
     * We need to test a string return
     */
    @Test
    public void testFindFirstMovieBookMusic() {
        //assertTrue(controller.itemByCategory().equals("Korra"));
    }
}
