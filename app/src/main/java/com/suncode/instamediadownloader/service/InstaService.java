package com.suncode.instamediadownloader.service;

import com.suncode.instamediadownloader.model.Graphql;
import com.suncode.instamediadownloader.model.InstaModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InstaService {
    @GET("?__a=1")
    public Call<InstaModel> getData();
}
