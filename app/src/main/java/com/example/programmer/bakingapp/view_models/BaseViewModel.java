package com.example.programmer.bakingapp.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.programmer.bakingapp.models.RecipeItem;
import com.example.programmer.bakingapp.repository.RepoRecipeBaki;

import java.util.List;

public class BaseViewModel extends AndroidViewModel {
    private RepoRecipeBaki baki;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        baki = RepoRecipeBaki.getInstance();
    }

    public LiveData<List<RecipeItem>> getRecipeItems() {
        return baki.getRecipeItemMutableLiveData();
    }
}
