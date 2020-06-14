package com.example.programmer.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientsItem implements Parcelable {

    public static final Parcelable.Creator<IngredientsItem> CREATOR = new Parcelable.Creator<IngredientsItem>() {
        @Override
        public IngredientsItem createFromParcel(Parcel source) {
            return new IngredientsItem(source);
        }

        @Override
        public IngredientsItem[] newArray(int size) {
            return new IngredientsItem[size];
        }
    };
    private Double quantity;
    private String measure;
    private String ingredient;

    public IngredientsItem(Double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public IngredientsItem() {
    }

    protected IngredientsItem(Parcel in) {
        this.quantity = in.readDouble();
        this.measure = in.readString();
        this.ingredient = in.readString();
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredient);
    }
}
