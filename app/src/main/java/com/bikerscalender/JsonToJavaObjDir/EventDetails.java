package com.bikerscalender.JsonToJavaObjDir;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventDetails {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("img_src")
    @Expose
    private String imgSrc;
    @SerializedName("srart_time")
    @Expose
    private String srartTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("from_location")
    @Expose
    private String fromLocation;
    @SerializedName("to_location")
    @Expose
    private String toLocation;
    @SerializedName("total_trip_time")
    @Expose
    private String totalTripTime;
    @SerializedName("event_url")
    @Expose
    private String eventUrl;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventDetails() {
    }

    /**
     *
     * @param id
     * @param srartTime
     * @param title
     * @param totalTripTime
     * @param imgSrc
     * @param description
     * @param eventUrl
     * @param fromLocation
     * @param toLocation
     * @param endTime
     */
    public EventDetails(Integer id, String description, String title, String imgSrc, String srartTime, String endTime, String fromLocation, String toLocation, String totalTripTime, String eventUrl) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.imgSrc = imgSrc;
        this.srartTime = srartTime;
        this.endTime = endTime;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.totalTripTime = totalTripTime;
        this.eventUrl = eventUrl;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The imgSrc
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     *
     * @param imgSrc
     * The img_src
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    /**
     *
     * @return
     * The srartTime
     */
    public String getSrartTime() {
        return srartTime;
    }

    /**
     *
     * @param srartTime
     * The srart_time
     */
    public void setSrartTime(String srartTime) {
        this.srartTime = srartTime;
    }

    /**
     *
     * @return
     * The endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     *
     * @param endTime
     * The end_time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @return
     * The fromLocation
     */
    public String getFromLocation() {
        return fromLocation;
    }

    /**
     *
     * @param fromLocation
     * The from_location
     */
    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    /**
     *
     * @return
     * The toLocation
     */
    public String getToLocation() {
        return toLocation;
    }

    /**
     *
     * @param toLocation
     * The to_location
     */
    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    /**
     *
     * @return
     * The totalTripTime
     */
    public String getTotalTripTime() {
        return totalTripTime;
    }

    /**
     *
     * @param totalTripTime
     * The total_trip_time
     */
    public void setTotalTripTime(String totalTripTime) {
        this.totalTripTime = totalTripTime;
    }

    /**
     *
     * @return
     * The eventUrl
     */
    public String getEventUrl() {
        return eventUrl;
    }

    /**
     *
     * @param eventUrl
     * The event_url
     */
    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

}