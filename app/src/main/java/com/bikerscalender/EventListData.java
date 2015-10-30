package com.bikerscalender;

/**
 * Created by Hitesh Goel on 2/9/15.
 */
public class EventListData {
    String title;
    String start_date;
    String from;
    String to;
    String total_time;
    String url_link;
    String image_url;
    String description;
    String group_name;

    public String getTitle(){ return this.title; }

    public String getStartDate(){
        return this.start_date;
    }

    public String getToLocation(){
        return this.to;
    }

    public String getFromLocation(){
        return this.from;
    }

    public String getUrlLink(){
        return this.url_link;
    }

    public String getTotalTime(){
        return this.total_time;
    }

    public String getImageUrlLink(){
        return this.image_url;
    }

    public String getDescription(){
        return this.description;
    }

    public String getGroupName(){
        return this.group_name;
    }
}
