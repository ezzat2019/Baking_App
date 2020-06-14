package com.example.programmer.bakingapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.programmer.bakingapp.models.RecipeItem;
import com.example.programmer.bakingapp.retrofit.RetrofitHelber;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoRecipeBaki {
    private static final RepoRecipeBaki ourInstance = new RepoRecipeBaki();
    private RetrofitHelber helber;
    private MutableLiveData<List<RecipeItem>> recipeItemMutableLiveData;

    private RepoRecipeBaki() {
        helber = RetrofitHelber.getInstance();
        recipeItemMutableLiveData = new MutableLiveData<>();
    }

    public static RepoRecipeBaki getInstance() {
        return ourInstance;
    }

    public MutableLiveData<List<RecipeItem>> getRecipeItemMutableLiveData() {
        helber.getApi().getRecipeItem().enqueue(new Callback<List<RecipeItem>>() {
            @Override
            public void onResponse(Call<List<RecipeItem>> call, Response<List<RecipeItem>> response) {
                recipeItemMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<List<RecipeItem>> call, Throwable t) {
                Log.e("eeeeeeeeee", t.getMessage());

            }
        });
        return recipeItemMutableLiveData;
    }
}
