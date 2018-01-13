package com.crawler;

import com.owlike.genson.Genson;
import com.scraper.Scraper;
import org.json.JSONException;
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
    private Genson genson;
    private Scraper scraper;
    private JSONObject jsonObject;

    /**
     * Constructor of the web crawler class, we create all the required objects.
     */
    public Crawler() {
        links = new ArrayList<>();
        genson = new Genson();
        scraper = new Scraper();
        jsonObject = new JSONObject();
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

    public String getSpecificItem(String URL, String name) {

        return "";
    }
}