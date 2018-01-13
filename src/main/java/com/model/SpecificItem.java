package com.model;

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