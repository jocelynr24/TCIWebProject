package com.scraper;


import com.owlike.genson.JsonBindingException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*; // Only needed if scraping a local File.
import java.util.HashMap;


import com.owlike.genson.Genson;


public class Scraper {

    public Scraper(){};


    /**
     * The method scrap() is inspecting the html page, searching for td and th balises, and stocks
     * the result in an HashMap, and then serialize this HashMap to returns the JSON containing
     * all the information about Movie, Books or Musics that the page contains.
     * @param url
     * @return a string which contains the json
     */

    public String scrap(String url){

        HashMap<String,String> map = new HashMap<>();

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String title = doc.title();

        Elements table = doc.select("tr");

        map.put("Title", title);

        for(Element tables : table){
            Elements tdh = tables.getElementsByTag("th");
            Elements tdd = tables.getElementsByTag("td");

            /* We need to parse the result from the getElementByTag because it contains
            * the balise tags and elements that we don't want to return*/
            String hhh = tdh.toString().replaceFirst("</th>","");
            String hh = hhh.replaceFirst("<th>","");
            String ddd = tdd.toString().replaceFirst("</td>","");
            String dd = ddd.replaceFirst("<td>","");

            map.put(hh,dd);

        }

        Genson genson = new Genson();
        String json = null;
        try {
            json = genson.serialize(map);
        } catch(JsonBindingException e){
            System.err.println("Could not parse input types");
        }

        return json;
    }
}
