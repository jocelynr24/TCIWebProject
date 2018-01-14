package com.model;

/**
 * Model class of the SpecificItem provided in the crawler.
 * Used by the method getSpecificItem() in the crawler.
 *
 * @see com.crawler.Crawler#getSpecificItem(String, String)
 */

public class SpecificItem {
    private long time;
    private Object result;

    public SpecificItem(long time, Object result) {
        this.time = time;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}