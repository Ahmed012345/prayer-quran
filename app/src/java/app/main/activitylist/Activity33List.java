package com.zaker.android.sapeh.app.main.activitylist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;

import androidx.core.content.ContextCompat;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.marcoscg.materialtoast.MaterialToast;
import com.melnykov.fab.FloatingActionButton;
import com.realpacific.clickshrinkeffect.ClickShrinkEffect;
import com.sackcentury.shinebuttonlib.ShineButton;

import com.zaker.android.sapeh.app.main.BounceTouchListener;
import com.zaker.android.sapeh.app.main.Bungee;
import com.zaker.android.sapeh.R;

import com.zaker.android.sapeh.app.main.MainRecActivity;
import com.zaker.android.sapeh.app.main.bubble.BubbleLayout;
import com.zaker.android.sapeh.app.main.cookiebar.CookieBar;
import com.zaker.android.sapeh.app.main.foltingtoast.FloatingToast;
import com.zaker.android.sapeh.app.main.recordview.*;


import java.util.concurrent.TimeUnit;


public class Activity33List extends SlidingUpBaseActivity<ObservableScrollView>
        implements ObservableScrollViewCallbacks {

    Context context = this;
    MediaPlayer m1,m2,m3,m4,m5,m6,m7,m8,m9,backb,finalpop;
    Toolbar toolbar;
    ScrollView scrollView;
    View header;
    ShineButton po_image,po_image1,po_image2,po_image3,po_image4,po_image5,
            po_image6,po_image7,po_image8,po_image9,po_image10,po_image11,
            po_image12,po_image13,po_image14,po_image15,po_image16,po_image17,
            po_image18,po_image19,po_image20,po_image21,po_image22,po_image23;


    RecordView recordView;
    RecordButton recordButton;
    ImageView img1,img2;
    BubbleLayout kal,kal1,kal2,kal3,kal4,kal5,kal6,badge,badge1,badge2,badge3,badge4,badge5,badge6,badge7,badge8,badge9,
            badge10,badge11,badge12,badge13,badge14,badge15,badge16;
    FloatingActionButton fab;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity33_list;

    }

    @Override
    protected ObservableScrollView createScrollable() {
        ObservableScrollView scrollView =  findViewById(R.id.scroll);
        scrollView.setScrollViewCallbacks(this);
        return scrollView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        header = findViewById(R.id.header_image_view);
        scrollView =  findViewById(R.id.scroll_view);
        BounceTouchListener bounceTouchListener = BounceTouchListener.create
                (scrollView, R.id.content,
                        new BounceTouchListener.OnTranslateListener() {
                            @Override
                            public void onTranslate(float translation) {
                                if (translation > 0) {
                                    float scale = ((2 * translation) /
                                            header.getMeasuredHeight()) + 1;
                                    header.setScaleX(scale);
                                    header.setScaleY(scale);
                                }
                            }
                        }
                );

        scrollView.setOnTouchListener(bounceTouchListener);

        toolbar =  findViewById(R.id.toolbar);

        backb = MediaPlayer.create(this, R.raw.backb);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                new MaterialToast(Activity33List.this)
                        .setMessage(getResources().getString(R.string.bybyback))
                        .setIcon(R.mipmap.ic_kaba1)
                        .setDuration(Toast.LENGTH_SHORT)
                        .setBackgroundColor(Color.parseColor("#C74C25"))
                        .show();
                Bungee.shrink(context);
                m1.stop();m2.stop();m3.stop();m4.stop();m5.stop();m6.stop();
                m7.stop();m8.stop();m9.stop();backb.start();
            }
        });

        po_image = findViewById(R.id.po_image);
        po_image1 = findViewById(R.id.po_image1);
        po_image2 = findViewById(R.id.po_image2);
        po_image3 = findViewById(R.id.po_image3);
        po_image4 = findViewById(R.id.po_image4);
        po_image5 = findViewById(R.id.po_image5);
        po_image6 = findViewById(R.id.po_image6);
        po_image7 = findViewById(R.id.po_image7);
        po_image8 = findViewById(R.id.po_image8);
        po_image9 = findViewById(R.id.po_image9);
        po_image10 = findViewById(R.id.po_image10);
        po_image11 = findViewById(R.id.po_image11);
        po_image12 = findViewById(R.id.po_image12);
        po_image13 = findViewById(R.id.po_image13);
        po_image14 = findViewById(R.id.po_image14);
        po_image15 = findViewById(R.id.po_image15);
        po_image16 = findViewById(R.id.po_image16);
        po_image17 = findViewById(R.id.po_image17);
        po_image18 = findViewById(R.id.po_image18);
        po_image19 = findViewById(R.id.po_image19);
        po_image20 = findViewById(R.id.po_image20);
        po_image21 = findViewById(R.id.po_image21);
        po_image22 = findViewById(R.id.po_image22);
        po_image23 = findViewById(R.id.po_image23);
        m1 = MediaPlayer.create(this, R.raw.al1emran2627);
        m2 = MediaPlayer.create(this, R.raw.al1yemran173174);
        m3 = MediaPlayer.create(this, R.raw.al1momenon9394);
        m4 = MediaPlayer.create(this, R.raw.mo1menon9798);
        m5 = MediaPlayer.create(this, R.raw.mo1menon118);
        m6 = MediaPlayer.create(this, R.raw.es1raa8082);
        m7 = MediaPlayer.create(this, R.raw.es1raa110111);
        m8 = MediaPlayer.create(this, R.raw.at1ouba128129);
        m9 = MediaPlayer.create(this, R.raw.ta1ha114);
        finalpop = MediaPlayer.create(this, R.raw.finalpop);

        po_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image.isChecked()){
                    finalpop.start();
                }
            }
        });
        po_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image1.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.sourah3), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image2.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.z2627), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image3.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.z173174), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image4.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.sourah23), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image5.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.z9394), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image6.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.z9798), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image7.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.z118), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image8.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.sourah17), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image9.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.z8082), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image10.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.z110111), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image11.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.sourah9), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image12.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.z128129), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image13.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.sourah20), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (po_image14.isChecked()){
                    finalpop.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.z114), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                }
            }
        });
        po_image15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po_image15.setChecked(false);
                if (m1.isPlaying()) {
                    m1.stop();
                    m1.prepareAsync();
                    m1.seekTo(0);
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                } else {
                    po_image15.setChecked(true);
                    m1.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#ffffff"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#C74C25"))
                            .show();    //Show toast at the specified fixed position
                    if (m2.isPlaying()) {
                        m2.stop();
                        m2.prepareAsync();
                        m2.seekTo(0);
                    }
                    if (m3.isPlaying()) {
                        m3.stop();
                        m3.prepareAsync();
                        m3.seekTo(0);
                    }
                    if (m4.isPlaying()) {
                        m4.stop();
                        m4.prepareAsync();
                        m4.seekTo(0);
                    }
                    if (m5.isPlaying()) {
                        m5.stop();
                        m5.prepareAsync();
                        m5.seekTo(0);
                    }
                    if (m6.isPlaying()) {
                        m6.stop();
                        m6.prepareAsync();
                        m6.seekTo(0);
                    }
                    if (m7.isPlaying()) {
                        m7.stop();
                        m7.prepareAsync();
                        m7.seekTo(0);
                    }
                    if (m8.isPlaying()) {
                        m8.stop();
                        m8.prepareAsync();
                        m8.seekTo(0);
                    }
                    if (m9.isPlaying()) {
                        m9.stop();
                        m9.prepareAsync();
                        m9.seekTo(0);
                    }
                    if(po_image16.isChecked())
                        po_image16.setChecked(false);
                    if (po_image17.isChecked())
                        po_image17.setChecked(false);
                    if (po_image18.isChecked())
                        po_image18.setChecked(false);
                    if (po_image19.isChecked())
                        po_image19.setChecked(false);
                    if (po_image20.isChecked())
                        po_image20.setChecked(false);
                    if (po_image21.isChecked())
                        po_image21.setChecked(false);
                    if (po_image22.isChecked())
                        po_image22.setChecked(false);
                    if (po_image23.isChecked())
                        po_image23.setChecked(false);
                }
            }
        });
        po_image16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po_image16.setChecked(false);
                if (m2.isPlaying()) {
                    m2.stop();
                    m2.prepareAsync();
                    m2.seekTo(0);
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                } else {
                    po_image16.setChecked(true);
                    m2.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#ffffff"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#C74C25"))
                            .show();    //Show toast at the specified fixed position
                    if (m1.isPlaying()) {
                        m1.stop();
                        m1.prepareAsync();
                        m1.seekTo(0);
                    }
                    if (m3.isPlaying()) {
                        m3.stop();
                        m3.prepareAsync();
                        m3.seekTo(0);
                    }
                    if (m4.isPlaying()) {
                        m4.stop();
                        m4.prepareAsync();
                        m4.seekTo(0);
                    }
                    if (m5.isPlaying()) {
                        m5.stop();
                        m5.prepareAsync();
                        m5.seekTo(0);
                    }
                    if (m6.isPlaying()) {
                        m6.stop();
                        m6.prepareAsync();
                        m6.seekTo(0);
                    }
                    if (m7.isPlaying()) {
                        m7.stop();
                        m7.prepareAsync();
                        m7.seekTo(0);
                    }
                    if (m8.isPlaying()) {
                        m8.stop();
                        m8.prepareAsync();
                        m8.seekTo(0);
                    }
                    if (m9.isPlaying()) {
                        m9.stop();
                        m9.prepareAsync();
                        m9.seekTo(0);
                    }
                    if(po_image15.isChecked())
                        po_image15.setChecked(false);
                    if (po_image17.isChecked())
                        po_image17.setChecked(false);
                    if (po_image18.isChecked())
                        po_image18.setChecked(false);
                    if (po_image19.isChecked())
                        po_image19.setChecked(false);
                    if (po_image20.isChecked())
                        po_image20.setChecked(false);
                    if (po_image21.isChecked())
                        po_image21.setChecked(false);
                    if (po_image22.isChecked())
                        po_image22.setChecked(false);
                    if (po_image23.isChecked())
                        po_image23.setChecked(false);
                }
            }
        });
        po_image17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po_image17.setChecked(false);
                if (m3.isPlaying()) {
                    m3.stop();
                    m3.prepareAsync();
                    m3.seekTo(0);
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                } else {
                    po_image17.setChecked(true);
                    m3.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#ffffff"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#C74C25"))
                            .show();    //Show toast at the specified fixed position
                    if (m2.isPlaying()) {
                        m2.stop();
                        m2.prepareAsync();
                        m2.seekTo(0);
                    }
                    if (m1.isPlaying()) {
                        m1.stop();
                        m1.prepareAsync();
                        m1.seekTo(0);
                    }
                    if (m4.isPlaying()) {
                        m4.stop();
                        m4.prepareAsync();
                        m4.seekTo(0);
                    }
                    if (m5.isPlaying()) {
                        m5.stop();
                        m5.prepareAsync();
                        m5.seekTo(0);
                    }
                    if (m6.isPlaying()) {
                        m6.stop();
                        m6.prepareAsync();
                        m6.seekTo(0);
                    }
                    if (m7.isPlaying()) {
                        m7.stop();
                        m7.prepareAsync();
                        m7.seekTo(0);
                    }
                    if (m8.isPlaying()) {
                        m8.stop();
                        m8.prepareAsync();
                        m8.seekTo(0);
                    }
                    if (m9.isPlaying()) {
                        m9.stop();
                        m9.prepareAsync();
                        m9.seekTo(0);
                    }
                    if(po_image15.isChecked())
                        po_image15.setChecked(false);
                    if (po_image16.isChecked())
                        po_image16.setChecked(false);
                    if (po_image18.isChecked())
                        po_image18.setChecked(false);
                    if (po_image19.isChecked())
                        po_image19.setChecked(false);
                    if (po_image20.isChecked())
                        po_image20.setChecked(false);
                    if (po_image21.isChecked())
                        po_image21.setChecked(false);
                    if (po_image22.isChecked())
                        po_image22.setChecked(false);
                    if (po_image23.isChecked())
                        po_image23.setChecked(false);
                }
            }
        });
        po_image18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po_image18.setChecked(false);
                if (m4.isPlaying()) {
                    m4.stop();
                    m4.prepareAsync();
                    m4.seekTo(0);
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                } else {
                    po_image18.setChecked(true);
                    m4.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#ffffff"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#C74C25"))
                            .show();    //Show toast at the specified fixed position
                    if (m2.isPlaying()) {
                        m2.stop();
                        m2.prepareAsync();
                        m2.seekTo(0);
                    }
                    if (m3.isPlaying()) {
                        m3.stop();
                        m3.prepareAsync();
                        m3.seekTo(0);
                    }
                    if (m1.isPlaying()) {
                        m1.stop();
                        m1.prepareAsync();
                        m1.seekTo(0);
                    }
                    if (m5.isPlaying()) {
                        m5.stop();
                        m5.prepareAsync();
                        m5.seekTo(0);
                    }
                    if (m6.isPlaying()) {
                        m6.stop();
                        m6.prepareAsync();
                        m6.seekTo(0);
                    }
                    if (m7.isPlaying()) {
                        m7.stop();
                        m7.prepareAsync();
                        m7.seekTo(0);
                    }
                    if (m8.isPlaying()) {
                        m8.stop();
                        m8.prepareAsync();
                        m8.seekTo(0);
                    }
                    if (m9.isPlaying()) {
                        m9.stop();
                        m9.prepareAsync();
                        m9.seekTo(0);
                    }
                    if(po_image15.isChecked())
                        po_image15.setChecked(false);
                    if (po_image16.isChecked())
                        po_image16.setChecked(false);
                    if (po_image17.isChecked())
                        po_image17.setChecked(false);
                    if (po_image19.isChecked())
                        po_image19.setChecked(false);
                    if (po_image20.isChecked())
                        po_image20.setChecked(false);
                    if (po_image21.isChecked())
                        po_image21.setChecked(false);
                    if (po_image22.isChecked())
                        po_image22.setChecked(false);
                    if (po_image23.isChecked())
                        po_image23.setChecked(false);
                }
            }
        });
        po_image19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po_image19.setChecked(false);
                if (m5.isPlaying()) {
                    m5.stop();
                    m5.prepareAsync();
                    m5.seekTo(0);
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                } else {
                    po_image19.setChecked(true);
                    m5.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#ffffff"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#C74C25"))
                            .show();    //Show toast at the specified fixed position
                    if (m2.isPlaying()) {
                        m2.stop();
                        m2.prepareAsync();
                        m2.seekTo(0);
                    }
                    if (m3.isPlaying()) {
                        m3.stop();
                        m3.prepareAsync();
                        m3.seekTo(0);
                    }
                    if (m4.isPlaying()) {
                        m4.stop();
                        m4.prepareAsync();
                        m4.seekTo(0);
                    }
                    if (m1.isPlaying()) {
                        m1.stop();
                        m1.prepareAsync();
                        m1.seekTo(0);
                    }
                    if (m6.isPlaying()) {
                        m6.stop();
                        m6.prepareAsync();
                        m6.seekTo(0);
                    }
                    if (m7.isPlaying()) {
                        m7.stop();
                        m7.prepareAsync();
                        m7.seekTo(0);
                    }
                    if (m8.isPlaying()) {
                        m8.stop();
                        m8.prepareAsync();
                        m8.seekTo(0);
                    }
                    if (m9.isPlaying()) {
                        m9.stop();
                        m9.prepareAsync();
                        m9.seekTo(0);
                    }
                    if(po_image15.isChecked())
                        po_image15.setChecked(false);
                    if (po_image16.isChecked())
                        po_image16.setChecked(false);
                    if (po_image17.isChecked())
                        po_image17.setChecked(false);
                    if (po_image18.isChecked())
                        po_image18.setChecked(false);
                    if (po_image20.isChecked())
                        po_image20.setChecked(false);
                    if (po_image21.isChecked())
                        po_image21.setChecked(false);
                    if (po_image22.isChecked())
                        po_image22.setChecked(false);
                    if (po_image23.isChecked())
                        po_image23.setChecked(false);

                }
            }
        });
        po_image20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po_image20.setChecked(false);
                if (m6.isPlaying()) {
                    m6.stop();
                    m6.prepareAsync();
                    m6.seekTo(0);
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                } else {
                    po_image20.setChecked(true);
                    m6.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#ffffff"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#C74C25"))
                            .show();    //Show toast at the specified fixed position
                    if (m2.isPlaying()) {
                        m2.stop();
                        m2.prepareAsync();
                        m2.seekTo(0);
                    }
                    if (m3.isPlaying()) {
                        m3.stop();
                        m3.prepareAsync();
                        m3.seekTo(0);
                    }
                    if (m4.isPlaying()) {
                        m4.stop();
                        m4.prepareAsync();
                        m4.seekTo(0);
                    }
                    if (m5.isPlaying()) {
                        m5.stop();
                        m5.prepareAsync();
                        m5.seekTo(0);
                    }
                    if (m1.isPlaying()) {
                        m1.stop();
                        m1.prepareAsync();
                        m1.seekTo(0);
                    }
                    if (m7.isPlaying()) {
                        m7.stop();
                        m7.prepareAsync();
                        m7.seekTo(0);
                    }
                    if (m8.isPlaying()) {
                        m8.stop();
                        m8.prepareAsync();
                        m8.seekTo(0);
                    }
                    if (m9.isPlaying()) {
                        m9.stop();
                        m9.prepareAsync();
                        m9.seekTo(0);
                    }
                    if(po_image15.isChecked())
                        po_image15.setChecked(false);
                    if (po_image16.isChecked())
                        po_image16.setChecked(false);
                    if (po_image17.isChecked())
                        po_image17.setChecked(false);
                    if (po_image18.isChecked())
                        po_image18.setChecked(false);
                    if (po_image19.isChecked())
                        po_image19.setChecked(false);
                    if (po_image21.isChecked())
                        po_image21.setChecked(false);
                    if (po_image22.isChecked())
                        po_image22.setChecked(false);
                    if (po_image23.isChecked())
                        po_image23.setChecked(false);
                }
            }
        });
        po_image21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po_image21.setChecked(false);
                if (m7.isPlaying()) {
                    m7.stop();
                    m7.prepareAsync();
                    m7.seekTo(0);
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                } else {
                    po_image21.setChecked(true);
                    m7.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#ffffff"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#C74C25"))
                            .show();    //Show toast at the specified fixed position
                    if (m2.isPlaying()) {
                        m2.stop();
                        m2.prepareAsync();
                        m2.seekTo(0);
                    }
                    if (m3.isPlaying()) {
                        m3.stop();
                        m3.prepareAsync();
                        m3.seekTo(0);
                    }
                    if (m4.isPlaying()) {
                        m4.stop();
                        m4.prepareAsync();
                        m4.seekTo(0);
                    }
                    if (m5.isPlaying()) {
                        m5.stop();
                        m5.prepareAsync();
                        m5.seekTo(0);
                    }
                    if (m6.isPlaying()) {
                        m6.stop();
                        m6.prepareAsync();
                        m6.seekTo(0);
                    }
                    if (m1.isPlaying()) {
                        m1.stop();
                        m1.prepareAsync();
                        m1.seekTo(0);
                    }
                    if (m8.isPlaying()) {
                        m8.stop();
                        m8.prepareAsync();
                        m8.seekTo(0);
                    }
                    if (m9.isPlaying()) {
                        m9.stop();
                        m9.prepareAsync();
                        m9.seekTo(0);
                    }
                    if(po_image15.isChecked())
                        po_image15.setChecked(false);
                    if (po_image16.isChecked())
                        po_image16.setChecked(false);
                    if (po_image17.isChecked())
                        po_image17.setChecked(false);
                    if (po_image18.isChecked())
                        po_image18.setChecked(false);
                    if (po_image19.isChecked())
                        po_image19.setChecked(false);
                    if (po_image20.isChecked())
                        po_image20.setChecked(false);
                    if (po_image22.isChecked())
                        po_image22.setChecked(false);
                    if (po_image23.isChecked())
                        po_image23.setChecked(false);
                }
            }
        });
        po_image22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po_image22.setChecked(false);
                if (m8.isPlaying()) {
                    m8.stop();
                    m8.prepareAsync();
                    m8.seekTo(0);
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                } else {
                    po_image22.setChecked(true);
                    m8.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#ffffff"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#C74C25"))
                            .show();    //Show toast at the specified fixed position
                    if (m2.isPlaying()) {
                        m2.stop();
                        m2.prepareAsync();
                        m2.seekTo(0);
                    }
                    if (m3.isPlaying()) {
                        m3.stop();
                        m3.prepareAsync();
                        m3.seekTo(0);
                    }
                    if (m4.isPlaying()) {
                        m4.stop();
                        m4.prepareAsync();
                        m4.seekTo(0);
                    }
                    if (m5.isPlaying()) {
                        m5.stop();
                        m5.prepareAsync();
                        m5.seekTo(0);
                    }
                    if (m6.isPlaying()) {
                        m6.stop();
                        m6.prepareAsync();
                        m6.seekTo(0);
                    }
                    if (m7.isPlaying()) {
                        m7.stop();
                        m7.prepareAsync();
                        m7.seekTo(0);
                    }
                    if (m1.isPlaying()) {
                        m1.stop();
                        m1.prepareAsync();
                        m1.seekTo(0);
                    }
                    if (m9.isPlaying()) {
                        m9.stop();
                        m9.prepareAsync();
                        m9.seekTo(0);
                    }
                    if(po_image15.isChecked())
                        po_image15.setChecked(false);
                    if (po_image16.isChecked())
                        po_image16.setChecked(false);
                    if (po_image17.isChecked())
                        po_image17.setChecked(false);
                    if (po_image18.isChecked())
                        po_image18.setChecked(false);
                    if (po_image19.isChecked())
                        po_image19.setChecked(false);
                    if (po_image20.isChecked())
                        po_image20.setChecked(false);
                    if (po_image21.isChecked())
                        po_image21.setChecked(false);
                    if (po_image23.isChecked())
                        po_image23.setChecked(false);
                }
            }
        });
        po_image23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po_image23.setChecked(false);
                if (m9.isPlaying()) {
                    m9.stop();
                    m9.prepareAsync();
                    m9.seekTo(0);
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#C74C25"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                            .show();    //Show toast at the specified fixed position
                } else {
                    po_image23.setChecked(true);
                    m9.start();
                    FloatingToast.makeToast(Activity33List.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                            .setBackgroundBlur(true)
                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                            .setTextColor(Color.parseColor("#ffffff"))
                            .setShadowLayer(5, 1, 1, Color.parseColor("#C74C25"))
                            .show();    //Show toast at the specified fixed position
                    if (m2.isPlaying()) {
                        m2.stop();
                        m2.prepareAsync();
                        m2.seekTo(0);
                    }
                    if (m3.isPlaying()) {
                        m3.stop();
                        m3.prepareAsync();
                        m3.seekTo(0);
                    }
                    if (m4.isPlaying()) {
                        m4.stop();
                        m4.prepareAsync();
                        m4.seekTo(0);
                    }
                    if (m5.isPlaying()) {
                        m5.stop();
                        m5.prepareAsync();
                        m5.seekTo(0);
                    }
                    if (m6.isPlaying()) {
                        m6.stop();
                        m6.prepareAsync();
                        m6.seekTo(0);
                    }
                    if (m7.isPlaying()) {
                        m7.stop();
                        m7.prepareAsync();
                        m7.seekTo(0);
                    }
                    if (m8.isPlaying()) {
                        m8.stop();
                        m8.prepareAsync();
                        m8.seekTo(0);
                    }
                    if (m1.isPlaying()) {
                        m1.stop();
                        m1.prepareAsync();
                        m1.seekTo(0);
                    }
                    if(po_image15.isChecked())
                        po_image15.setChecked(false);
                    if (po_image16.isChecked())
                        po_image16.setChecked(false);
                    if (po_image17.isChecked())
                        po_image17.setChecked(false);
                    if (po_image18.isChecked())
                        po_image18.setChecked(false);
                    if (po_image19.isChecked())
                        po_image19.setChecked(false);
                    if (po_image20.isChecked())
                        po_image20.setChecked(false);
                    if (po_image21.isChecked())
                        po_image21.setChecked(false);
                    if (po_image22.isChecked())
                        po_image22.setChecked(false);

                }
            }
        });


        GradientDrawable shapeDrawable= (GradientDrawable) ContextCompat.getDrawable(this,R.drawable.recv_bg_mic);
        shapeDrawable.setColor(getResources().getColor(R.color.shape33));
        recordView = findViewById(R.id.record_view);
        recordButton = findViewById(R.id.record_button);
        recordButton.setBackground(shapeDrawable);

        //IMPORTANT
        recordButton.setRecordView(recordView);

        //ListenForRecord must be false ,otherwise onClick will not be called
        recordButton.setOnRecordClickListener(new OnRecordClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RecordButton", "RECORD BUTTON CLICKED");
            }
        });


        //Cancel Bounds is when the Slide To Cancel text gets before the timer . default is 8
        recordView.setCancelBounds(8);
        recordView.setSmallMicColor(Color.parseColor("#c2185b"));
        recordView.setSlideToCancelText(getResources().getString(R.string.slide_to_cancel));
        //disable Sounds
        recordView.setSoundEnabled(true);
        //prevent recording under one Second (it's false by default)
        recordView.setLessThanSecondAllowed(false);
        //set Custom sounds onRecord
        //you can pass 0 if you don't want to play sound in certain state
        //change slide To Cancel Text Color
        recordView.setSlideToCancelTextColor(getResources().getColor(R.color.gray_light));
        //change slide To Cancel Arrow Color
        recordView.setSlideToCancelArrowColor(getResources().getColor(R.color.gray_light));
        //change Counter Time (Chronometer) color
        recordView.setCounterTimeColor(getResources().getColor(R.color.gray_dark));
        recordView.setCustomSounds(R.raw.record_start, R.raw.record_finished, R.raw.record_error);


        recordView.setOnRecordListener(new OnRecordListener() {
            @Override
            public void onStart() {
                Log.d("RecordView", "onStart");
                if (checkRecordPermission2()) {
                    if (checkStoragePermission2()) {
                        //Start or stop recording
                        presenter.startRecording(getApplicationContext());
                    }
                }
                CookieBar.build(Activity33List.this)
                        .setTitle(getResources().getString(R.string.hold_button_to_record))
                        .setTitleColor(R.color.al3333)
                        .setMessage(getResources().getString(R.string.hold_button_to_record2))
                        .setMessageColor(R.color.default_message_color)
                        .setIcon(R.drawable.recv_ic_mic_white)
                        .setBackgroundColor(R.color.al33333333)
                        .setCookiePosition(Gravity.TOP)
                        .setAnimationIn(R.anim.slide_in_left, R.anim.slide_in_left)
                        .setAnimationOut(R.anim.slide_out_left, R.anim.slide_out_left)
                        .setDuration(3000)
                        .show();
            }

            @Override
            public void onCancel() {
                presenter.cancelRecording();
                presenter.deleteActiveRecord(true);
            }

            @Override
            public void onFinish(long recordTime) {
                String time = getHumanTimeText(recordTime);
                new MaterialToast(context)
                        .setMessage(getResources().getString(R.string.recordtoast) + time)
                        .setIcon(R.drawable.time)
                        .setDuration(Toast.LENGTH_LONG)
                        .setBackgroundColor(getResources().getColor(R.color.shape33))
                        .show();
                Log.d("RecordView", "onFinish");
                Log.d("RecordTime", time);

                presenter.stopRecording(false);

                startActivity(new Intent(context, MainRecActivity.class));
                Bungee.shrink(context);
            }


            @Override
            public void onLessThanSecond() {
                Log.d("RecordView", "onLessThanSecond");
            }
        });


        recordView.setOnBasketAnimationEndListener(new OnBasketAnimationEnd() {
            @Override
            public void onAnimationEnd() {
                Log.d("RecordView", "Basket Animation Finished");
            }
        });

        kal = findViewById(R.id.kal); kal1 = findViewById(R.id.kal1); kal2 = findViewById(R.id.kal2);
        kal3 = findViewById(R.id.kal3); kal4 = findViewById(R.id.kal4); kal5 = findViewById(R.id.kal5);
        kal6 = findViewById(R.id.kal6); badge = findViewById(R.id.badge); badge1 = findViewById(R.id.badge1);
        badge2 = findViewById(R.id.badge2); badge3 = findViewById(R.id.badge3); badge4 = findViewById(R.id.badge4);
        badge5 = findViewById(R.id.badge5); badge6 = findViewById(R.id.badge6); badge7 = findViewById(R.id.badge7);
        badge8 = findViewById(R.id.badge8); badge9 = findViewById(R.id.badge9); badge10 = findViewById(R.id.badge10);
        badge11 = findViewById(R.id.badge11); badge12 = findViewById(R.id.badge12); badge13 = findViewById(R.id.badge13);
        badge14 = findViewById(R.id.badge14); badge15 = findViewById(R.id.badge15); badge16 = findViewById(R.id.badge16);
        img1 = findViewById(R.id.img1); img2 = findViewById(R.id.img2); fab = findViewById(R.id.fab);

        new ClickShrinkEffect(kal); new ClickShrinkEffect(kal1);
        new ClickShrinkEffect(kal2); new ClickShrinkEffect(kal3);
        new ClickShrinkEffect(kal4); new ClickShrinkEffect(kal5);
        new ClickShrinkEffect(kal6);   new ClickShrinkEffect(badge);
        new ClickShrinkEffect(badge1); new ClickShrinkEffect(badge2);
        new ClickShrinkEffect(badge3); new ClickShrinkEffect(badge4);
        new ClickShrinkEffect(badge5); new ClickShrinkEffect(badge6);
        new ClickShrinkEffect(badge7); new ClickShrinkEffect(badge8);
        new ClickShrinkEffect(badge9); new ClickShrinkEffect(badge10);
        new ClickShrinkEffect(badge11); new ClickShrinkEffect(badge12);
        new ClickShrinkEffect(badge13); new ClickShrinkEffect(badge14);
        new ClickShrinkEffect(badge15); new ClickShrinkEffect(badge16);
        new ClickShrinkEffect(img1); new ClickShrinkEffect(img2);
        new ClickShrinkEffect(fab);

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        new MaterialToast(Activity33List.this)
                .setMessage(getResources().getString(R.string.bybyback))
                .setIcon(R.mipmap.ic_kaba1)
                .setDuration(Toast.LENGTH_SHORT)
                .setBackgroundColor(Color.parseColor("#C74C25"))
                .show();
        Bungee.shrink(context);
        m1.stop();m2.stop();m3.stop();m4.stop();m5.stop();m6.stop();
        m7.stop();m8.stop();m9.stop();backb.start();
    }
    @SuppressLint("DefaultLocale")
    private String getHumanTimeText(long milliseconds) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milliseconds),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
    }

}
