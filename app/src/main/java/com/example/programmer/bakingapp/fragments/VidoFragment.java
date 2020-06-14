package com.example.programmer.bakingapp.fragments;


import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.programmer.bakingapp.R;
import com.example.programmer.bakingapp.RecipesActivity;
import com.example.programmer.bakingapp.models.RecipeItem;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class VidoFragment extends Fragment {
    // var
    private static ExoPlayer player;
    // ui
    private ImageView imageView;
    private TextView txt_ins;
    private PlayerView simpleExoPlayerView;
    private CardView cardView;
    private Toolbar toolbar;
    private RecipeItem item;
    private Integer i = null;


    public VidoFragment() {
        // Required empty public constructor
        item = RecipesActivity.itemm;
        releasePlayer();
    }


    public VidoFragment(int i) {
        this.i = i;
        item = RecipesActivity.itemm;
        releasePlayer();
    }

    public static void releasePlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vido, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardView = view.findViewById(R.id.card);
        toolbar = view.findViewById(R.id.toolbar);
        imageView=view.findViewById(R.id.img_no);


        if (player != null) {
            simpleExoPlayerView = null;
            releasePlayer();

        } else if (cardView.getVisibility() == View.GONE) {
            if (player != null) {
                player.stop();
                player.release();
                player = null;
            }

        }


        if (i == null) {
            i = 0;
        }

        buildViews(view);
        inailiazePlayer();


    }

    private void buildViews(View view) {
        txt_ins = view.findViewById(R.id.txt_step_ins);
        simpleExoPlayerView = view.findViewById(R.id.video_view);


        fillTextView(view);
    }

    private void fillTextView(View view) {
        txt_ins.setText(item.getSteps().get(i).getDescription() + "");
    }

    private void inailiazePlayer() {
        if (player == null) {

            player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector(), new DefaultLoadControl());
            player.stop();

            simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.no_video));
            simpleExoPlayerView.setPlayer(player);
            String url = "";
            if (!item.getSteps().get(i).getVideoURL().equals("")) {
                url = item.getSteps().get(i).getVideoURL();
                imageView.setVisibility(View.GONE);
                simpleExoPlayerView.setVisibility(View.VISIBLE);
            } else if (!item.getSteps().get(i).getThumbnailURL().equals("")) {

                url = item.getSteps().get(i).getThumbnailURL();
                imageView.setVisibility(View.GONE);
                simpleExoPlayerView.setVisibility(View.VISIBLE);

            } else {
                imageView.setVisibility(View.VISIBLE);
                simpleExoPlayerView.setVisibility(View.INVISIBLE);
                Picasso.with(getContext()).load(R.drawable.no_video)
                        .into(imageView);

                url = "";
            }


            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                    Util.getUserAgent(getContext().getApplicationContext(), "Bakking"));
// This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(url));

// Prepare the player with the source.
            player.prepare(videoSource);
            if (url.equals("")) {
                simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.no_video));
            }
            player.setRepeatMode(Player.REPEAT_MODE_ALL);
            player.setPlayWhenReady(true);


        }


    }


}
