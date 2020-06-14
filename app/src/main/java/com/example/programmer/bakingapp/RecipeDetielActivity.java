package com.example.programmer.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.bakingapp.adapters.StepAdapter;
import com.example.programmer.bakingapp.models.IngredientsItem;
import com.example.programmer.bakingapp.models.RecipeItem;
import com.example.programmer.bakingapp.models.StepsItem;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetielActivity extends AppCompatActivity implements StepAdapter.SetOnItemClick {
    public static String ss = null;
    // ui
    private TextView txt_intgradients;
    private RecyclerView recyclerView;
    // var
    private List<IngredientsItem> ingredientsItemList;
    private List<StepsItem> stepsItemList;
    private StepAdapter stepAdapter;
    private RecipeItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detiel);
        if (getIntent().hasExtra("ezzat")) {
            item = getIntent().getParcelableExtra("ezzat");
            // Required empty public constructor
            ingredientsItemList = new ArrayList<>(item.getIngredients());
            stepsItemList = new ArrayList<>(item.getSteps());
            stepAdapter = new StepAdapter(this);
            stepAdapter.setList(stepsItemList);


            getSupportActionBar().setTitle(item.getName());
            fillTextingradints();

            buildRecycle();

        }


    }

    private void buildRecycle() {
        recyclerView = findViewById(R.id.rec_steps);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(stepAdapter);
    }

    private void fillTextingradints() {
        txt_intgradients = findViewById(R.id.txt_intgradines);
        if (ingredientsItemList.isEmpty()) {
            return;

        }
        txt_intgradients.setText("");
        for (int i = 0; i < ingredientsItemList.size(); i++) {
            txt_intgradients.append("ingredient (" + (i + 1) + ") " + ingredientsItemList.get(i).getIngredient() + "\n");
        }
        ss = txt_intgradients.getText().toString();


    }

    @Override
    public void onClick(int pos) {
        Intent intent = new Intent(getApplicationContext(), ViewRecipeStepActivity.class);
        intent.putExtra("ezzat", item);
        intent.putExtra("pos", pos);

        startActivity(intent);

    }


}

