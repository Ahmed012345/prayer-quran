package com.zaker.android.sapeh.app.main.activitymain;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.marcoscg.materialtoast.MaterialToast;
import com.zaker.android.sapeh.R;

public class YouSounaTube extends LocalizationActivity {

    Context context;

    String videoUrl = "S0Q4gqBUs7c";
    ProgressDialog pd;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_souna_tube);


        videoView = findViewById(R.id.videoView);
        pd = new ProgressDialog(this);
        pd.setMessage("");
        pd.setCancelable(true);


    }

    private void playvideo(){
        try {
            if (NetworkChecker.isNetworkConnected(YouSounaTube.this)) {
                getWindow().setFormat(PixelFormat.TRANSLUCENT);
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);

                Uri videoUri = Uri.parse(videoUrl);
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(videoUri);
                videoView.requestFocus();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        pd.dismiss();
                        videoView.start();
                    }
                });


                new MaterialToast(context)
                        .setMessage(getResources().getString(R.string.error_failed_to_init_player))
                        .setIcon(R.mipmap.ic_kaba1)
                        .setDuration(Toast.LENGTH_LONG)
                        .setBackgroundColor(Color.parseColor("#CF8E08"))
                        .show();

            } else {
                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}