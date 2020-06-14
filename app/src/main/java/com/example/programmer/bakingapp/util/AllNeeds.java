package com.example.programmer.bakingapp.util;

import com.example.programmer.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public final class AllNeeds {

    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";
    private static List<Integer> listImagesRecpies;

    public static List<Integer> getListImagesRecpies() {

        listImagesRecpies = new ArrayList<>();
        listImagesRecpies.add(R.drawable.nuttela);
        listImagesRecpies.add(R.drawable.brownies);
        listImagesRecpies.add(R.drawable.yellowcake);
        listImagesRecpies.add(R.drawable.cheesecake);

        return listImagesRecpies;
    }
}
