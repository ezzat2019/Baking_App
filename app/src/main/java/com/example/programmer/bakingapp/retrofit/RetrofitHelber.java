package com.example.programmer.bakingapp.retrofit;

import com.example.programmer.bakingapp.ui.BakkingApi;
import com.example.programmer.bakingapp.util.AllNeeds;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelber {
    private static final RetrofitHelber ourInstance = new RetrofitHelber();
    private Retrofit retrofit;
    private BakkingApi api;

    private RetrofitHelber() {
        retrofit = new Retrofit.Builder().baseUrl(AllNeeds.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(BakkingApi.class);
    }

    public static RetrofitHelber getInstance() {
        return ourInstance;
    }

    public BakkingApi getApi() {
        return api;
    }
}
