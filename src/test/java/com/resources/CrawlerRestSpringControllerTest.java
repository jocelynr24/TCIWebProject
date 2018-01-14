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
        when(crawlerMock.getSpecificItem(controller.url, "y")).thenReturn("La légende de Korra");
        when(crawlerMock.generalCrawler(controller.url)).thenReturn("La légende de Korra");
    }

    /**
     * Test of searchSpecificItem
     * We need to test a string return
     */
    @Test
    public void testSpecificItem() {
        assertTrue(controller.searchSpecificItem("y").equals("La légende de Korra"));
    }

    /**
     * Test of searchSpecificItem
     * This is a stub method
     * We replace the used functions and put the attended result
     * We need to test a string return
     */
    @Test
    public void testInformation()  {

        assertTrue(controller.information().equals("La légende de Korra"));
    }


    /**
     * Test of findFirstMovieBookMusic
     * We need to test a string return
     */
    @Test
    public void testFindFirstMovieBookMusic() {

        controller = new CrawlerRestSpringController();

        controller.setCrawler(new Crawler() {
            public String getAllItems(String URL) {
                return "Korra";
            }
        });
        String d=controller.itemByCategory();
        assertTrue(controller.itemByCategory().equals("Korra"));
    }
}
