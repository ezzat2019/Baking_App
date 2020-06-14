package com.example.programmer.bakingapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

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
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

public class ViewRecipeStepActivity extends AppCompatActivity {
    // var
    private ExoPlayer player;
    // ui
    private TextView txt_ins;
    private PlayerView simpleExoPlayerView;
    private CardView cardView;
    private Toolbar toolbar;
    private RecipeItem item;
    private int i;
    private long pos = 0;
    private ImageView imageView;
    private Boolean isReady = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_step);
        if (savedInstanceState != null) {
            pos = savedInstanceState.getLong("poss");
            isReady = savedInstanceState.getBoolean("is_ready");
        }

        Log.d("aaaaaaa", "create");
        cardView = findViewById(R.id.card);
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.img_no);

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


        if (getIntent().hasExtra("ezzat")) {


            i = getIntent().getIntExtra("pos", -1);
            Log.d("eeeeeee", i + "");
            item = getIntent().getParcelableExtra("ezzat");
            toolbar.setTitle(item.getName());

            buildViews();

        }


    }

    private void buildViews() {
        txt_ins = findViewById(R.id.txt_step_ins);
        simpleExoPlayerView = findViewById(R.id.video_view);


        fillTextView();
    }

    private void fillTextView() {
        txt_ins.setText(item.getSteps().get(i).getDescription() + "");
    }

    private void inailiazePlayer() {
        if (player == null) {

            player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector(), new DefaultLoadControl());

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
                Picasso.with(getApplicationContext()).load(R.drawable.no_video)
                        .into(imageView);

                url = "";
            }


            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(),
                    Util.getUserAgent(getApplicationContext(), "Bakking"));
// This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(url));

// Prepare the player with the source.
            player.prepare(videoSource);
            if (url.equals("")) {
                simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.no_video));
            }
            player.setRepeatMode(Player.REPEAT_MODE_ALL);
            if (pos > 0) {
                player.setPlayWhenReady(isReady);
                player.seekTo(pos);
                Log.d("aaaaaa", "on");
            } else
                player.setPlayWhenReady(true);


        } else
            Toast.makeText(this, "nooooooooo", Toast.LENGTH_SHORT).show();


    }

    private void releasePlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    public void next(View view) {

        if (i < item.getSteps().size() - 1) {
            i++;
            Intent intent = new Intent(getApplicationContext(), ViewRecipeStepActivity.class);
            intent.putExtra("ezzat", item);
            intent.putExtra("pos", i);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "this is end step", Toast.LENGTH_SHORT).show();
        }
    }

    public void previous(View view) {

        if (i > 0) {
            i--;
            Intent intent = new Intent(getApplicationContext(), ViewRecipeStepActivity.class);
            intent.putExtra("ezzat", item);
            intent.putExtra("pos", i);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "goto next steps", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            inailiazePlayer();
        }
        Log.d("aaaaaaa", "start");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || player == null) {
            inailiazePlayer();
        }
        Log.d("aaaaaaa", "resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            pos = player.getContentPosition();
            isReady = player.getPlayWhenReady();
            releasePlayer();
            Log.d("aaaaaaa", "pause");
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            Log.d("aaaaaaa", player.getContentPosition() + "");
            pos = player.getContentPosition();
            isReady = player.getPlayWhenReady();
            releasePlayer();
            Log.d("aaaaaaa", "stop");
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("aaaaaa", "res");

        Log.d("aaaaaa", pos + "   aa");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("poss", pos);
        outState.putBoolean("is_ready", isReady);

        Log.d("aaaaaaa", "save");
    }
}
