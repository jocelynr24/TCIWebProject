package com.resources;

import com.crawler.Crawler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Spring Controller tests
 *
 * @author Julien Conte
 */

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
        when(crawlerMock.getSpecificItem(controller.url, "Name")).thenReturn("La légende de Korra");
    }

    /**
     * Test of searchSpecificItem
     * We need to test a string return
     */
    @Test
    public void testSpecificItem() {
        assertTrue(controller.searchSpecificItem("Name").equals("La légende de Korra"));
    }

    /**
     * Test of findFirstMovieBookMusic
     * We need to test a string return
     */
    @Test
    public void testAllItems() {
        controller = new CrawlerRestSpringController();
        controller.setCrawler(new Crawler() {
            public String getAllItems(String URL) {
                return "Korra";
            }
        });
        String d = controller.searchAllItems();
        assertTrue(controller.searchAllItems().equals("Korra"));
    }
}