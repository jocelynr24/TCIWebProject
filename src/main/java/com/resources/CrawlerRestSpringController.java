package com.resources;

import com.crawler.Crawler;
import org.springframework.web.bind.annotation.*;

/**
 * Spring Controller
 *
 * @author Julien Conte
 */

@RestController
public class CrawlerRestSpringController {

    /**
     * Our "Database" => Crawler
     */
    private Crawler crawler = new Crawler();
    String url = "https://i398507.hera.fhict.nl/tci/";

    /**
     * For the stub test, we need to have a setter for the crawler
     * @param crawler
     */
    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    /**
     * @param item Title of a music/book/movie
     * @Method Get
     * @return String/json result (movie/book/music)
     */
    @RequestMapping(value="/searchspecificitem/{item}", method = RequestMethod.GET, produces = "application/json")
    public String searchSpecificItem( @PathVariable String item)  {
        return  crawler.getSpecificItem(url.toLowerCase(), item);
    }

    /**
     * @Method Get
     * @return String/json result containing data of all the movies, books and music met on the website
     */
    @RequestMapping(value="/searchallitems", method = RequestMethod.GET, produces = "application/json")
    public String searchAllItems(){
        return crawler.getAllItems(url.toLowerCase());
    }
}