package com.example.programmer.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RecipeItem implements Parcelable {
    public static final Parcelable.Creator<RecipeItem> CREATOR = new Parcelable.Creator<RecipeItem>() {
        @Override
        public RecipeItem createFromParcel(Parcel source) {
            return new RecipeItem(source);
        }

        @Override
        public RecipeItem[] newArray(int size) {
            return new RecipeItem[size];
        }
    };
    private int id;
    private String name;
    private int servings;
    private String image;
    private List<IngredientsItem> ingredients;
    private List<StepsItem> steps;

    public RecipeItem(int id, String name, int servings, String image, List<IngredientsItem> ingredients, List<StepsItem> steps) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public RecipeItem() {
    }

    protected RecipeItem(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.servings = in.readInt();
        this.image = in.readString();
        this.ingredients = in.createTypedArrayList(IngredientsItem.CREATOR);
        this.steps = in.createTypedArrayList(StepsItem.CREATOR);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<IngredientsItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsItem> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepsItem> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsItem> steps) {
        this.steps = steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.servings);
        dest.writeString(this.image);
        dest.writeTypedList(this.ingredients);
        dest.writeTypedList(this.steps);
    }
}
