package com.example.project1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiinterface {
    @GET("https://api.genderize.io/")
    Call<model> getmodel(@Query("name")String name);
}
