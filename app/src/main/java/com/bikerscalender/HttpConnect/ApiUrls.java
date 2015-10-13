package com.bikerscalender.HttpConnect;

import com.bikerscalender.JsonToJavaObjDir.EventDetails;
import com.bikerscalender.JsonToJavaObjDir.ResultData;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Hitesh Goel on 10/9/15.
 */

public interface ApiUrls {
    @GET("/{url}/travel_limitless.json")
    Call<ResultData> getEvents( @Path("url") String url );
}
