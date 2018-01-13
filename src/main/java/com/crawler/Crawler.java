package com.crawler;

import com.model.AllItems;
import com.model.SpecificItem;
import com.owlike.genson.Genson;
import com.scraper.Scraper;
import com.serializer.CrawlerSerializer;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

/**
 * Class containing the web crawler methods.
 * In this class, we get the URLs on a page and store them in a list.
 * Then, we call the web scrapper to get all the information on a web page.
 * Finally, depending on which method is called, we return the data to the API.
 *
 * @author Jocelyn Routin
 * @author Adrien Cosson
 */

public class Crawler {
    private List<String> links;
    private CrawlerSerializer crawlerSerializer;
    private Genson genson;
    private Scraper scraper;
    private JSONObject jsonObject;

    /**
     * Constructor of the web crawler class, we create all the required objects.
     */
    public Crawler() {
        links = new ArrayList<>();
        crawlerSerializer = new CrawlerSerializer();
        genson = new Genson();
        scraper = new Scraper();
        jsonObject = new JSONObject();
    }

	/**
     * This method is getting the link that we give to retrieve the domain and then call the getPageLinks() method in order to start the crawling.
     * @param URL The URL that will be used to detect the domain of the website that has to be crawled
     */
    public void webCrawler(String URL){
        String regex = "(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?";

        if (URL.matches(regex)) {
            int domainIndex = URL.indexOf("/", URL.indexOf("/", URL.indexOf("/") + 1) + 1);
            String domain = URL.substring(0, domainIndex);
            getPageLinks(URL, domain);
        } else {
            throw new IllegalArgumentException("illegal format of Url [" + URL + "]");
        }
    }

    /**
     * This method permits to get all the URLs of a web page if this web page has a valid URL and belongs to the same domain.
     * @param URL The URL we want to get other links (e.g.: "http://website.com/page?id=123").
     * @param domain The domain of the web page (e.g.: "http://website.com").
     */
    public boolean getPageLinks(String URL, String domain) {
        String regex = "(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?";

        if(URL.matches(regex)){
            if (!links.contains(URL)) {
                try {
                    if(URL.startsWith(domain)){
                        links.add(URL);
                        Document document = Jsoup.connect(URL).get();
                        Elements linksOnPage = document.select("a[href]");

                        for (Element page : linksOnPage) {
                            getPageLinks(page.attr("abs:href"), domain);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("For '" + URL + "': " + e.getMessage());
                }
            }
        } else {
            throw new IllegalArgumentException("The URL is not correct");
        }

        return true;
    }

    /**
     * This function makes use of the webCrawler to get the first Movie, Book and Music it meets on the website and store the information inside a JSON formatted String
     * @param URL The URL we want to get other links (e.g.: "http://website.com/page?id=123").
     * @return A JSON formatted String that contains the information of the first Movie, Book and music met on the website, starting from the given URL.
     */
    public String getAllItems(String URL) {
        long startTime = System.nanoTime();
        String json;
        AllItems allItems = new AllItems();
        String returned = "";
        Integer ID;
        String regex = "(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?";

        if (URL.matches(regex)) {
            webCrawler(URL);
            for(Integer index = 0; links.size() <= index; index++){
                json = scraper.scrap(links.get(index));
                if(json != null){
                    jsonObject = new JSONObject(json);
					if(jsonObject.has("Category")){
						ID = Integer.valueOf(links.get(index).split("=")[1]);
						if(jsonObject.getString("Category").equals("Books")){
							allItems.addBook(crawlerSerializer.serializeToBook(jsonObject, ID));
						} else if(jsonObject.getString("Category").equals("Movies")){
							allItems.addMovie(crawlerSerializer.serializeToMovie(jsonObject, ID));
						} else if(jsonObject.getString("Category").equals("Music")){
							allItems.addMusic(crawlerSerializer.serializeToMusic(jsonObject, ID));
						}
					}
                }
            }
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            allItems.setTime(duration);
            returned = genson.serialize(allItems);
        } else {
            throw new IllegalArgumentException("illegal format of Url [" + URL + "]");
        }
        return returned;
    }

    /**
     * This method permits to find a specific item on the whole website by giving the name of what we want to search.
     * @param URL The URL we want to get other links (e.g.: "http://website.com/page?id=123").
     * @param name The domain of the web page (e.g.: "http://website.com").
     * @return A string in a JSON format containing all the information of the element we searched, else an empty string if nothing was found.
     */
    public String getSpecificItem(String URL, String name) {
        Integer index = 0;
        Integer ID;
        long startTime, endTime, duration;
        Object specificItemInfo;
        SpecificItem specificItem;
        String scrapperResult;
        String specificItemJSON = "";
        String validLinkRegex = "(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?";

        if(URL.matches(validLinkRegex)){
            startTime = System.nanoTime();

            webCrawler(URL);

            do{
                scrapperResult = scraper.scrap(links.get(index));
                if(scrapperResult != null){
                    jsonObject = new JSONObject(scrapperResult);
                }
                index++;
            }while((links.size() > index) && !(jsonObject.getString("Title").equals(name)));

            if(jsonObject.getString("Title").equals(name)){
                ID = Integer.valueOf(links.get(index - 1).split("=")[1]);

                endTime = System.nanoTime();
                duration = endTime - startTime;

                switch(jsonObject.getString("Category")){
                    case "Books":
                        specificItemInfo = crawlerSerializer.serializeToBook(jsonObject, ID);
                        specificItem = new SpecificItem(duration, specificItemInfo);
                        specificItemJSON = genson.serialize(specificItem);
                        break;
                    case "Movies":
                        specificItemInfo = crawlerSerializer.serializeToMovie(jsonObject, ID);
                        specificItem = new SpecificItem(duration, specificItemInfo);
                        specificItemJSON = genson.serialize(specificItem);
                        break;
                    case "Music":
                        specificItemInfo = crawlerSerializer.serializeToMusic(jsonObject, ID);
                        specificItem = new SpecificItem(duration, specificItemInfo);
                        specificItemJSON = genson.serialize(specificItem);
                        break;
                }
            } else {
                specificItemJSON = "";
            }
        } else {
            throw new IllegalArgumentException("The URL is not correct");
        }

        return specificItemJSON;
    }

    public String generalCrawler(String URL){

        return "";
    }
}