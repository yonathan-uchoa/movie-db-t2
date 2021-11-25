package com.example.moviedb.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {
    @GET("search/multi")
    Call<MoviesSearch> getPost(
            @QueryMap Map<String, String> options
            );

}
