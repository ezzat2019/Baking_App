package com.example.programmer.bakingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.bakingapp.adapters.RecipeAdapter;
import com.example.programmer.bakingapp.models.RecipeItem;
import com.example.programmer.bakingapp.view_models.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity implements RecipeAdapter.SetOnItemClick {
    private static final String TAG_FRAG = "is_on";
    public static RecipeItem itemm = null;
    public List<RecipeItem> recipeItemList;
    // ui
    @BindView(R.id.rec_recipes)
    RecyclerView recyclerView;
    @BindView(R.id.v1)
    View view;
    // var
    private BaseViewModel viewModel;
    private RecipeAdapter recipeAdapter;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        checkInternet(savedInstanceState);
    }

    public void checkInternet(Bundle savedInstanceState) {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() == null) {
            showDialog(savedInstanceState);
        } else {
            viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

            bulidRecycle(savedInstanceState);


        }

    }

    private void bulidRecycle(Bundle savedInstanceState) {

        recyclerView.setHasFixedSize(true);
        if (view.getVisibility() == View.GONE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }


        recipeAdapter = new RecipeAdapter(this);

        viewModel.getRecipeItems().observe(this, new Observer<List<RecipeItem>>() {
            @Override
            public void onChanged(List<RecipeItem> recipeItems) {
                recipeItemList = new ArrayList<>(recipeItems);
                recipeAdapter.setList(recipeItemList);
                recyclerView.setAdapter(recipeAdapter);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void showDialog(final Bundle savedInstanceState) {
        new AlertDialog.Builder(RecipesActivity.this).setTitle("No Internet Connection")
                .setPositiveButton("try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        checkInternet(savedInstanceState);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setMessage("turn on internet connection and pressed try again").create().show();
    }

    @Override
    public void onClick(int pos) {
        Intent intent;
        if (view.getVisibility() == View.GONE) {
            intent = new Intent(getApplicationContext(), TabletColletActivity.class);

        } else {
            intent = new Intent(getApplicationContext(), RecipeDetielActivity.class);
        }

        intent.putExtra("ezzat", recipeItemList.get(pos));
        itemm = recipeItemList.get(pos);
        startActivity(intent);


    }


}
