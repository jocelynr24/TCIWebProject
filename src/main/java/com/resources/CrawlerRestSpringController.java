package com.resources;

import com.crawler.Crawler;
import org.springframework.web.bind.annotation.*;

/***
 * Spring Controller
 */
@RestController
public class CrawlerRestSpringController {
    /**
     * Our "Database" => Crawler
     */
    Crawler crawler = new Crawler();

    /**
     * For the stub test, we need to have a setter for the crawler
     * @param crawler
     */
    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    /**
     *
     * @param item (Title of a music/book/movie)
     * @Method Get
     * @return String/json result (movie/book/music)
     */
    @RequestMapping(value="/search_specific_item/{item}", method = RequestMethod.GET, produces = "application/json")
    public String searchSpecificItem(@PathVariable("value=item") String item)  {
        return  crawler.generalCrawler(item.toLowerCase());
    }

    /**
     * @Method Get
     * @return String/json result (search's information)
     */
    @RequestMapping(value="/information", method = RequestMethod.GET, produces = "application/json")
    public String information(){
        return crawler.information();

    }

    /**
     * @Method Get
     * @return String/json result (search's information)
     */
    @RequestMapping(value="/item_by_category", method = RequestMethod.GET, produces = "application/json")
    public String itemByCategory(){
        return crawler.findFirstMovieBookMusic();
    }

}
