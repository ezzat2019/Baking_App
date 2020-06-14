package com.example.programmer.bakingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.programmer.bakingapp.fragments.RecipeDetielFragment1;
import com.example.programmer.bakingapp.fragments.VidoFragment;

public class TabletColletActivity extends AppCompatActivity implements RecipeDetielFragment1.OnClickOnFrag {

    // ui

    //var


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet_collet);

        getSupportActionBar().setTitle(getString(R.string.app_name));
        RecipeDetielFragment1 fragment1 = new RecipeDetielFragment1(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().add(R.id.fragment_detiel_intgardient, fragment1);
        transaction.commit();

        VidoFragment fragment2 = new VidoFragment();
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction().add(R.id.fragment_detiel_istep, fragment2);
        transaction2.commit();

    }

    @Override
    public void onClick(int pos) {
        VidoFragment fragment3 = new VidoFragment(pos);
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_detiel_istep, fragment3);
        transaction2.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        VidoFragment.releasePlayer();
    }
}


