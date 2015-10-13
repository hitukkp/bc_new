package com.bikerscalender.JsonToJavaObjDir;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultData {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<EventDetails> data = new ArrayList<EventDetails>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ResultData() {
    }

    /**
     *
     * @param message
     * @param responseCode
     * @param data
     */
    public ResultData(Integer responseCode, String message, List<EventDetails> data) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    /**
     *
     * @return
     * The responseCode
     */
    public Integer getResponseCode() {
        return responseCode;
    }

    /**
     *
     * @param responseCode
     * The response_code
     */
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The data
     */
    public List<EventDetails> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<EventDetails> data) {
        this.data = data;
    }

}