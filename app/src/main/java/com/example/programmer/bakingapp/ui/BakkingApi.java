package com.example.programmer.bakingapp.ui;

import com.example.programmer.bakingapp.models.RecipeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BakkingApi {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<RecipeItem>> getRecipeItem();
}
