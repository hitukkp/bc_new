package com.bikerscalender.HttpConnect;

import retrofit.Retrofit;

/**
 * Created by applect on 10/9/15.
 */
public class HttpServiceProvider {

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit builder = new Retrofit.Builder().baseUrl(baseUrl).build();
        return builder.create(serviceClass);
    }
}
