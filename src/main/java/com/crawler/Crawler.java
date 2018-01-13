package com.crawler;

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
        // Regex to detect if a URL is in a good format
        String regex = "(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?";

        // If the URL is in a good format
        if (URL.matches(regex)) {
            // We detect the domain of the URL by getting the index of the 3rd slash of the URL
            int domainIndex = URL.indexOf("/", URL.indexOf("/", URL.indexOf("/") + 1) + 1);
            // Then we store the part of the URL which is called the domain into a string
            // The string should contain, in our example, "https://i398507.hera.fhict.nl"
            String domain = URL.substring(0, domainIndex);
            // Call the getPageLinks using the URL parameter that we already gave to the webCrawler method
            // and the domain of the website so it won't search through other website
            getPageLinks(URL, domain);
        } else {
            // Else, if the URL is not in a good format, we throw an exception
            throw new IllegalArgumentException("illegal format of Url [" + URL + "]");
        }

    }

    /**
     * This method permits to get all the URLs of a web page if this web page has a valid URL and belongs to the same domain.
     * @param URL The URL we want to get other links (e.g.: "http://website.com/page?id=123").
     * @param domain The domain of the web page (e.g.: "http://website.com").
     */
    public boolean getPageLinks(String URL, String domain) {
        // Regex to detect if a URL is in a good format
        String regex = "(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?";

        // If the URL is in a good format
        if(URL.matches(regex)){
            // If the URL is not already contained in the links list
            if (!links.contains(URL)) {
                try {
                    // If the URL belongs to the domain
                    if(URL.startsWith(domain)){
                        // We add this URL to the links list
                        links.add(URL);
                        // We get the content of the web page
                        Document document = Jsoup.connect(URL).get();
                        // We get all the links available on this page
                        Elements linksOnPage = document.select("a[href]");

                        // For each link on this page
                        for (Element page : linksOnPage) {
                            // We call this method another time to get all the links on each page
                            getPageLinks(page.attr("abs:href"), domain);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("For '" + URL + "': " + e.getMessage());
                }
            }
        } else {
            // Else, if the URL is not in a good format, we throw an exception
            throw new IllegalArgumentException("The URL is not correct");
        }

        // We return true to specify that the method finished correctly
        return true;
    }

    public String getOnePerItem(String URL) {

        return "";
    }

    /**
     * This method permits to find a specific item on the whole website by giving the name of what we want to search.
     * @param URL The URL we want to get other links (e.g.: "http://website.com/page?id=123").
     * @param name The domain of the web page (e.g.: "http://website.com").
     * @return A string in a JSON format containing all the information of the element we searched, else an empty string if nothing was found.
     */
    public String getSpecificItem(String URL, String name) {
        // Integer for the index incrementer in the do-while statement
        Integer index = 0;
        // Integer for the ID of the element found
        Integer ID;
        // Long values to get the search time in milliseconds
        long startTime, endTime, duration;
        // Generic object to store the resulting object we found
        Object specificItemInfo;
        // The specificItem we want to return
        SpecificItem specificItem;
        // String to store the JSON that the scrapper returns
        String scrapperResult;
        // String to store the JSON the method will return
        String specificItemJSON = "";
        // Regex to detect if a URL is in a good format
        String validLinkRegex = "(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?";

        // If the URL is in a valid form (detected thanks to the regex)
        if(URL.matches(validLinkRegex)){
            // We start the timer
            startTime = System.nanoTime();

            // We call the webCrawler() with the URL to detect the others links on the web page
            webCrawler(URL);

            // We get the result from the scrapper until it gives the good item or if there is no longer links in the list
            do{
                // We scrap the website for a specific link
                scrapperResult = scraper.scrap(links.get(index));
                // If there was something to scrap
                if(scrapperResult != null){
                    // We store the result in a JSON object
                    jsonObject = new JSONObject(scrapperResult);
                }
                // We increment the index to text the next link in the list
                index++;
            }while((links.size() > index) && !(jsonObject.getString("Title").equals(name)));

            // If we found something in the do-while statement
            if(jsonObject.getString("Title").equals(name)){
                // We get the ID of this specific item
                ID = Integer.valueOf(links.get(index - 1).split("=")[1]);

                // We store the current time
                endTime = System.nanoTime();
                // And we compare the difference between the start time and the end time to get the total time
                duration = endTime - startTime;

                // We get the category of the JSON object (book, movie, or music)
                switch(jsonObject.getString("Category")){
                    // If it is a book
                    case "Books":
                        // We serialize the JSON object into a book object and we add its ID
                        specificItemInfo = crawlerSerializer.serializeToBook(jsonObject, ID);
                        // We create a SpecificItem object with the information of the previous object, plus the duration
                        specificItem = new SpecificItem(duration, specificItemInfo);
                        // We serialize the final object into a string to send it
                        specificItemJSON = genson.serialize(specificItem);
                        break;
                    // If it is a movie
                    case "Movies":
                        // We serialize the JSON object into a movie object and we add its ID
                        specificItemInfo = crawlerSerializer.serializeToMovie(jsonObject, ID);
                        // We create a SpecificItem object with the information of the previous object, plus the duration
                        specificItem = new SpecificItem(duration, specificItemInfo);
                        // We serialize the final object into a string to send it
                        specificItemJSON = genson.serialize(specificItem);
                        break;
                    // If it is a music
                    case "Music":
                        // We serialize the JSON object into a music object and we add its ID
                        specificItemInfo = crawlerSerializer.serializeToMusic(jsonObject, ID);
                        // We create a SpecificItem object with the information of the previous object, plus the duration
                        specificItem = new SpecificItem(duration, specificItemInfo);
                        // We serialize the final object into a string to send it
                        specificItemJSON = genson.serialize(specificItem);
                        break;
                }
            } else {
                // Else, if no object found, we have to send an empty string
                specificItemJSON = "";
            }
        } else {
            // Else, if it is not in a good format, we throw an exception
            throw new IllegalArgumentException("The URL is not correct");
        }

        // We return the string in JSON format if it succeeded, else an empty string if it failed
        return specificItemJSON;
    }
}