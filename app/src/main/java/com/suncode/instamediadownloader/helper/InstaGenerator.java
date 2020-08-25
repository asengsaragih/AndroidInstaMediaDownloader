package com.suncode.instamediadownloader.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstaGenerator {
    public static Retrofit build(String url) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
