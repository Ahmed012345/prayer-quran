package com.zaker.android.sapeh.app.main.activitymain;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marcoscg.materialtoast.MaterialToast;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.zaker.android.sapeh.R;

import org.jetbrains.annotations.NotNull;

public class YouMakaTube extends LocalizationActivity {

    YouTubePlayerView youTubePlayerView;
    Context context;
    private DatabaseReference mFirebaseDatabase;
    String videoIdMaka = "S0Q4gqBUs7c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_maka_tube);


        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        if (NetworkChecker.isNetworkConnected(YouMakaTube.this)) {
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                    youTubePlayer.loadVideo(videoIdMaka, 0);
                }
                @Override
                public void onError(@NotNull YouTubePlayer youTubePlayer, PlayerConstants.@NotNull PlayerError error) {
                    new MaterialToast(context)
                            .setMessage(getResources().getString(R.string.error_failed_to_init_player))
                            .setIcon(R.mipmap.ic_kaba1)
                            .setDuration(Toast.LENGTH_LONG)
                            .setBackgroundColor(Color.parseColor("#CF8E08"))
                            .show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }

}