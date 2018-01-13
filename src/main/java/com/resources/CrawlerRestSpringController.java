package com.resources;

import com.crawler.Crawler;
import org.springframework.web.bind.annotation.*;

/***
 * Spring Controller
 *
 * @author Julien Conte
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
	 * @param URL (link to crawl the website)
     * @param item (Title of a music/book/movie)
     * @Method Get
     * @return String/json result (movie/book/music)
     */
    @RequestMapping(value="/searchspecificitem/{URL}/{item}", method = RequestMethod.GET, produces = "application/json")
    public String searchSpecificItem(@PathVariable String URL, @PathVariable String item)  {
        return  crawler.getSpecificItem(URL.toLowerCase(), item);
    }

//    /**
//	 * @param URL (link to crawl the website)
//     * @Method Get
//     * @return String/json result (search's information)
//     */
//    @RequestMapping(value="/information/{URL}", method = RequestMethod.GET, produces = "application/json")
//    public String information(@PathVariable String URL){
//        return crawler.generalCrawler(URL.toLowerCase());
//
//    }

    /**
	 * @param URL (link to crawl the website)
     * @Method Get
     * @return String/json result containing data of the first movie, the first book and the first music met
     */
    @RequestMapping(value="/itembycategory/{URL}", method = RequestMethod.GET, produces = "application/json")
    public String itemByCategory(@PathVariable String URL){

        return crawler.getAllItems(URL.toLowerCase());

    }

}
