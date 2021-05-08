package com.zaker.android.sapeh.app.main.activitymain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.View;

import java.util.Calendar;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.zaker.android.sapeh.app.main.Bungee;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.main.foltingtoast.FloatingToast;
import com.zaker.android.sapeh.app.main.aboutpage.AboutPage;
import com.zaker.android.sapeh.app.main.aboutpage.Element;

public class Main4Activity extends LocalizationActivity {

    Context context = this;
    boolean doubleBackToExitPressedOnce = false;
    MediaPlayer backb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        simulateDayNight(3);
        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");

        View aboutPage = new AboutPage(this)
                .setCustomFont("fonts/yad.ttf")
                .isRTL(false)
                .addItem(new Element().setTitle("Version 2.9"))
                .addItem(adsElement)
                .setImage(R.drawable.icic)
                .addGroup("Connect with us")
                .addEmail("asmmya7@gmail.com")
                .addWebsite("mybluesky.site")
                .addGitHub("Ahmed012345")
                .addFacebook("kraro0o0o")
                .addTwitter("A7md_c3d")
                .addYoutube("UCYg6-xM8lEL6RAGwJ5BFG7A")
                .addPlayStore("com.zaker.android.sapeh")
                .addMoreAppsFromUs("com.horaspolice")
                .addItem(getCopyRightsElement())
                .create();
        setContentView(aboutPage);



        backb = MediaPlayer.create(this, R.raw.backb);



    }


    Element getCopyRightsElement () {
        Element copyRightsElement = new Element();
        @SuppressLint({"StringFormatInvalid", "LocalSuppress"})
        final String copyrights = String.format(getString(R.string.copy_right),
                Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
        copyRightsElement.setIconTint(R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingToast.makeToast(Main4Activity.this, getResources().getString(R.string.album_title), FloatingToast.LENGTH_TOO_LONG)
                        .setGravity(FloatingToast.GRAVITY_BOTTOM)
                        .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                        .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                        .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                        .setBackgroundBlur(true)
                        .setFloatDistance(FloatingToast.DISTANCE_LONG)
                        .setTextColor(Color.parseColor("#FED663"))
                        .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                        .show();    //Show toast at the specified fixed position
            }
        });
        return copyRightsElement;
    }


    void simulateDayNight(int currentSetting) {
        final int DAY = 0;
        final int NIGHT = 1;
        final int FOLLOW_SYSTEM = 3;

        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else if (currentSetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);


        }
    }
    @Override
    public void onBackPressed () {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            backb.start();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        FloatingToast.makeToast(Main4Activity.this, getResources().getString(R.string.finalback), FloatingToast.LENGTH_TOO_LONG)
                .setGravity(FloatingToast.GRAVITY_BOTTOM)
                .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                .setBackgroundBlur(true)
                .setFloatDistance(FloatingToast.DISTANCE_LONG)
                .setTextColor(Color.parseColor("#ffffff"))
                .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                .show();    //Show toast at the specified fixed position

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
                Bungee.shrink(context);

            }
        }, 2000);
    }
}

