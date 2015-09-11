package com.bikerscalender.HttpConnect;

import com.bikerscalender.DataJavaObjects.Events;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by applect on 10/9/15.
 */
public interface ApiUrls {
    @GET("/autocomplete/json")
    void listPlaces(
            @Query("sensor") String sensor,
            Callback<Events> cb
    );
}
