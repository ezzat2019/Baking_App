package com.example.programmer.bakingapp.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.bakingapp.R;
import com.example.programmer.bakingapp.RecipesActivity;
import com.example.programmer.bakingapp.adapters.StepAdapter;
import com.example.programmer.bakingapp.models.IngredientsItem;
import com.example.programmer.bakingapp.models.RecipeItem;
import com.example.programmer.bakingapp.models.StepsItem;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetielFragment1 extends Fragment implements StepAdapter.SetOnItemClick {


    public OnClickOnFrag onClickOnFrag;
    // ui
    private TextView txt_intgradients;
    private RecyclerView recyclerView;
    // var
    private List<IngredientsItem> ingredientsItemList;
    private List<StepsItem> stepsItemList;
    private StepAdapter stepAdapter;
    private RecipeItem item;

    public RecipeDetielFragment1() {
        item = RecipesActivity.itemm;
    }

    public RecipeDetielFragment1(OnClickOnFrag onClickOnFrag) {
        this.onClickOnFrag = onClickOnFrag;
        item = RecipesActivity.itemm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ingredientsItemList = new ArrayList<>(item.getIngredients());
        stepsItemList = new ArrayList<>(item.getSteps());
        stepAdapter = new StepAdapter(this);
        stepAdapter.setList(stepsItemList);
        return inflater.inflate(R.layout.fragment_recipe_detiel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillTextingradints(view);

        buildRecycle(view);


    }

    private void buildRecycle(View view) {
        recyclerView = view.findViewById(R.id.rec_steps);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(stepAdapter);
    }

    private void fillTextingradints(View view) {
        txt_intgradients = view.findViewById(R.id.txt_intgradines);
        if (ingredientsItemList.isEmpty()) {
            return;

        }
        txt_intgradients.setText("");
        for (int i = 0; i < ingredientsItemList.size(); i++) {
            txt_intgradients.append("ingredient (" + (i + 1) + ") " + ingredientsItemList.get(i).getIngredient() + "\n");
        }


    }

    @Override
    public void onClick(int pos) {
        onClickOnFrag.onClick(pos);

    }

    public interface OnClickOnFrag {
        void onClick(int pos);
    }
}
