package com.harinder.e_assistant;


public class announcementItem {

    String name,time,annText;

    public announcementItem(String name, String time, String annText) {
        this.name = name;
        this.time = time;
        this.annText = annText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAnnText() {
        return annText;
    }

    public void setAnnText(String annText) {
        this.annText = annText;
    }
}
