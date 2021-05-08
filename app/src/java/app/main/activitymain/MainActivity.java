package com.zaker.android.sapeh.app.main.activitymain;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Window;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import com.airbnb.lottie.RenderMode;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.cleveroad.sy.cyclemenuwidget.CycleMenuWidget;
import com.cleveroad.sy.cyclemenuwidget.OnMenuItemClickListener;
import com.cleveroad.sy.cyclemenuwidget.OnStateChangedListener;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.hanks.htextview.base.HTextView;
import com.marcoscg.materialtoast.MaterialToast;
import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.OnBoomListenerAdapter;
import com.nightonke.boommenu.Util;
import com.realpacific.clickshrinkeffect.ClickShrinkEffect;
import com.thanosfisherman.wifiutils.WifiUtils;
import com.zaker.android.sapeh.*;

import com.zaker.android.sapeh.app.main.*;
import com.zaker.android.sapeh.app.main.activitymain.expandingcards.Main5Activity;
import com.zaker.android.sapeh.app.main.finestwebview.FinestWebView;
import com.zaker.android.sapeh.app.main.guillotine.animation.GuillotineAnimation;
import com.zaker.android.sapeh.app.main.guillotine.interfaces.GuillotineListener;
import com.zaker.android.sapeh.app.main.bubble.BubbleLayout;

import java.util.Calendar;
import java.util.HashMap;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.zaker.android.sapeh.app.main.foltingtoast.FloatingToast;
import com.zaker.android.sapeh.app.main.easysharedpreferences.EasySharedPreference;

public class MainActivity extends LocalizationActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseCrashlytics crashlytics;

    String[] sentences;
    HTextView hTextView;
    int mCounter;

    GuillotineAnimation mGuillotineAnimation;
    boolean isOpened = true;

    boolean doubleBackToExitPressedOnce = false;

    private static final long RIPPLE_DURATION = 250;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.root) FrameLayout root;
    @BindView(R.id.content_hamburger) View contentHamburger;
    @BindView(R.id.bmb) BoomMenuButton bmb;

    Context context = this;
    MediaPlayer b1,backb,closek,openk,finalpop;

    String message = "رابط التطبيق App Link";
    String url = "https://play.google.com/store/apps/details?id=com.zaker.android.sapeh";

    String egyptLanguageCode = "ar";
    String chinaLanguageCode = "zh";
    String czechLanguageCode = "cs";
    String holandLanguageCode = "nl";
    String londonLanguagwCode = "en";
    String franceLanguageCode = "fr";
    String germanyLanguageCode = "de";
    String indoniseLanguageCode = "in";
    String malyseLanguageCode = "ms";
    String italyLanguageCode = "it";
    String japanLanguageCode = "ja";
    String koreaLanguageCode = "ko";
    String iranLanguageCode = "fa";
    String polandLanguageCode = "pl";
    String protogalLanguageCode = "pt";
    String romaniaLanguageCode = "ro";
    String russiaLanguageCode = "ru";
    String spianLanguageCode = "es";
    String thalandLanguageCode = "th";
    String turkyLanguageCode = "tr";
    String indiaLanguageCode = "hi";
    String bngalideshLanguageCode = "bn";
    String pakistanLanguageCode = "ur";
    String swideshLanguageCode = "sv";
    String albaniLanguageCode = "sq";
    String azrabiganLanguageCode = "az";
    String bosnaLanguageCode = "bs";
    String bulgaryLanguageCode = "bg";
    String nigeraLanguageCode = "ha";
    String norwegLanguageCode = "no";
    String somailyLanguageCode = "so";
    String kenyaLanguageCode = "sw";

    GradientDrawable ar,zh,cs,nl,en,fr,de,in,ms,it,ja,ko,fa,pl,pt,ro,ru,es,th,tr,hi,bn,ur,sv,sq,az,bs,bg,ha,no,so,sw;

    BubbleLayout frist1,frst,tow2,tow,fan,fan2,three3,three,four4,four;

    CycleMenuWidget cycleMenuWidget, cycleMenuWidget2;
    AnimatedGradientTextView menu;
    FrameLayout ff;
    private static final String FB_RC_KEY_TITLE="update_title";
    private static final String FB_RC_KEY_DESCRIPTION="update_description";
    private static final String FB_RC_KEY_FORCE_UPDATE_VERSION="force_update_version";
    private static final String FB_RC_KEY_LATEST_VERSION="latest_version";

    AppUpdateDialog appUpdateDialog;

    FirebaseRemoteConfig mFirebaseRemoteConfig;

    @BindView(R.id.animation_view) LottieAnimationView animation_view;

    ImageButton btn_rec;
    final String KEY_WEB = "WEB";

    @SuppressLint("LogNotTimber")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Move this to where you establish a user session
        logUser();
        setContentView(R.layout.activity_main);
        checkAppUpdate();
        ButterKnife.bind(this);
        FontManger.getInstance(getApplicationContext().getAssets());
        finalpop = MediaPlayer.create(this, R.raw.finalpop);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
        assert am != null;
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        @SuppressLint("InflateParams") final View guillotineMenu =
            LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);

        frst = findViewById(R.id.frst);
        frst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingToast.makeToast(MainActivity.this, getResources().getString(R.string.profile), FloatingToast.LENGTH_TOO_LONG)
                    .setGravity(FloatingToast.GRAVITY_BOTTOM)
                    .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                    .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                    .setBackgroundBlur(true)
                    .setFloatDistance(FloatingToast.DISTANCE_LONG)
                    .setTextColor(Color.parseColor("#27a3dd"))
                    .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                    .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                    .show();    //Show toast at the specified fixed position
            }
        });

        frist1 = findViewById(R.id.frist1);
        frist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, Main2Activity.class));
                Bungee.shrink(context);
                new MaterialToast(context)
                    .setMessage(getResources().getString(R.string.profile))
                    .setIcon(R.mipmap.ic_kaba1)
                    .setDuration(Toast.LENGTH_LONG)
                    .setBackgroundColor(Color.parseColor("#27a3dd"))
                    .show();
                mGuillotineAnimation.close();
            }
        });

        tow = findViewById(R.id.tow);
        tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FloatingToast.makeToast(MainActivity.this, getResources().getString(R.string.dawat_alanbyaa), FloatingToast.LENGTH_TOO_LONG)
                    .setGravity(FloatingToast.GRAVITY_BOTTOM)
                    .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                    .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                    .setBackgroundBlur(true)
                    .setFloatDistance(FloatingToast.DISTANCE_LONG)
                    .setTextColor(Color.parseColor("#E56353"))
                    .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                    .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                    .show();    //Show toast at the specified fixed position
            }
        });

        tow2 = findViewById(R.id.tow2);
        tow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, Main22Activity.class));
                Bungee.shrink(context);
                new MaterialToast(context)
                    .setMessage(getResources().getString(R.string.dawat_alanbyaa))
                    .setIcon(R.mipmap.ic_kaba1)
                    .setDuration(Toast.LENGTH_LONG)
                    .setBackgroundColor(Color.parseColor("#E56353"))
                    .show();
                mGuillotineAnimation.close();

            }
        });

        fan = findViewById(R.id.fan);
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FloatingToast.makeToast(MainActivity.this, getResources().getString(R.string.fantitl2), FloatingToast.LENGTH_TOO_LONG)
                    .setGravity(FloatingToast.GRAVITY_BOTTOM)
                    .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                    .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                    .setBackgroundBlur(true)
                    .setFloatDistance(FloatingToast.DISTANCE_LONG)
                    .setTextColor(Color.parseColor("#46CAA6"))
                    .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                    .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                    .show();    //Show toast at the specified fixed position
            }
        });

        fan2 = findViewById(R.id.fan2);
        fan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, Main5Activity.class));
                Bungee.shrink(context);
                new MaterialToast(context)
                    .setMessage(getResources().getString(R.string.fantitl2))
                    .setIcon(R.mipmap.ic_kaba1)
                    .setDuration(Toast.LENGTH_LONG)
                    .setBackgroundColor(Color.parseColor("#46CAA6"))
                    .show();
                mGuillotineAnimation.close();

            }
        });

        three = findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FloatingToast.makeToast(MainActivity.this, getResources().getString(R.string.activity), FloatingToast.LENGTH_TOO_LONG)
                    .setGravity(FloatingToast.GRAVITY_BOTTOM)
                    .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                    .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                    .setBackgroundBlur(true)
                    .setFloatDistance(FloatingToast.DISTANCE_LONG)
                    .setTextColor(Color.parseColor("#dc8744"))
                    .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                    .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                    .show();    //Show toast at the specified fixed position
            }
        });

        three3 = findViewById(R.id.three3);
        three3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, Main3Activity.class));
                Bungee.shrink(context);
                new MaterialToast(context)
                    .setMessage(getResources().getString(R.string.activity))
                    .setIcon(R.mipmap.ic_kaba1)
                    .setDuration(Toast.LENGTH_LONG)
                    .setBackgroundColor(Color.parseColor("#dc8744"))
                    .show();
                mGuillotineAnimation.close();

            }
        });

        four = findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FloatingToast.makeToast(MainActivity.this, getResources().getString(R.string.settings), FloatingToast.LENGTH_TOO_LONG)
                    .setGravity(FloatingToast.GRAVITY_BOTTOM)
                    .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                    .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                    .setBackgroundBlur(true)
                    .setFloatDistance(FloatingToast.DISTANCE_LONG)
                    .setTextColor(Color.parseColor("#FED663"))
                    .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                    .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                    .show();    //Show toast at the specified fixed position
            }
        });

        four4 = findViewById(R.id.four4);
        four4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, Main4Activity.class));
                Bungee.shrink(context);
                new MaterialToast(context)
                    .setMessage(getResources().getString(R.string.settings))
                    .setIcon(R.mipmap.ic_kaba1)
                    .setDuration(Toast.LENGTH_LONG)
                    .setBackgroundColor(Color.parseColor("#FED663"))
                    .show();
                mGuillotineAnimation.close();
            }
        });

        closek = MediaPlayer.create(this, R.raw.closek);
        openk = MediaPlayer.create(this, R.raw.openk);
        mGuillotineAnimation = new GuillotineAnimation.GuillotineBuilder(guillotineMenu,
        guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
        .setActionBarViewForAnimation(toolbar)
        .setStartDelay(RIPPLE_DURATION)
        .setClosedOnStart(true)
        .setGuillotineListener(new GuillotineListener() {
            @Override
            public void onGuillotineOpened() {
                openk.start();
                isOpened = true;

            }
            @Override
            public void onGuillotineClosed() {
                closek.start();
                isOpened = false;

            }
        })
        .build();

        btn_rec = findViewById(R.id.btn_rec);
        btn_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, MainRecActivity.class));
                Bungee.shrink(context);
            }
        });


        bmb = findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setShareLineLength(30);
        bmb.setShareLineWidth(4);
        bmb.setDotRadius(9);

        float w = Util.dp2px(80);
        float h = Util.dp2px(96);
        float h_0_5 = h / 2;
        float h_1_5 = h * 1.5f;

        float hm = bmb.getButtonHorizontalMargin();
        float vm = bmb.getButtonVerticalMargin();
        float vm_0_5 = vm / 2;
        float vm_1_5 = vm * 1.5f;

        bmb.getCustomButtonPlacePositions().add(new PointF(-w - hm, -h_1_5 - vm_1_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(      0, -h_1_5 - vm_1_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(+w + hm, -h_1_5 - vm_1_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(-w - hm, -h_0_5 - vm_0_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(      0, -h_0_5 - vm_0_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(+w + hm, -h_0_5 - vm_0_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(-w - hm, +h_0_5 + vm_0_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(      0, +h_0_5 + vm_0_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(+w + hm, +h_0_5 + vm_0_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(-w - hm, +h_1_5 + vm_1_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(      0, +h_1_5 + vm_1_5));
        bmb.getCustomButtonPlacePositions().add(new PointF(+w + hm, +h_1_5 + vm_1_5));

        TextOutsideCircleButton.Builder facebook = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.facebook)
            .normalTextRes(R.string.sharefacebook)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {


                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.FACEBOOK)
                        .url(url)
                        .build();
                    shareInstance.share();

                }
            });
        bmb.addBuilder(facebook);

        TextOutsideCircleButton.Builder twitter = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.twitter)
            .normalTextRes(R.string.sharetwitter)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.TWITTER)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(twitter);

        TextOutsideCircleButton.Builder google = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.gmail)
            .normalTextRes(R.string.sharegmail)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.GOOGLE)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(google);

        TextOutsideCircleButton.Builder whatsapp = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.whatsapp)
            .normalTextRes(R.string.sharewhatapp)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.WHATSAPP)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(whatsapp);

        TextOutsideCircleButton.Builder instagram = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.instagram)
            .normalTextRes(R.string.shareinstagram)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.INSTAGRAM)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(instagram);

        TextOutsideCircleButton.Builder tumblr = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.tumblr)
            .normalTextRes(R.string.sharetumblr)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.TUMBLR)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(tumblr);

        TextOutsideCircleButton.Builder linkedin = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.linkedin)
            .normalTextRes(R.string.sharelinkedin)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.LINKED_IN)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(linkedin);

        TextOutsideCircleButton.Builder mail = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.viber)
            .normalTextRes(R.string.shareviber)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.VIBER)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(mail);

        TextOutsideCircleButton.Builder snapchat = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.snapchat)
            .normalTextRes(R.string.sharesnapchat)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.SNAPCHAT)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(snapchat);

        TextOutsideCircleButton.Builder skype = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.skype)
            .normalTextRes(R.string.shareskype)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.SKYPE)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(skype);

        TextOutsideCircleButton.Builder telegram = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.telegram)
            .normalTextRes(R.string.sharetelegram)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.TELEGRAM)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(telegram);

        TextOutsideCircleButton.Builder reddit = new TextOutsideCircleButton.Builder()
            .normalImageRes(R.drawable.reddit)
            .normalTextRes(R.string.sharereddit)
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Shareable shareInstance = new Shareable.Builder(context)
                        .message(message)
                        .socialChannel(Shareable.Builder.REDDIT)
                        .url(url)
                        .build();
                    shareInstance.share();
                }
            });
        bmb.addBuilder(reddit);

        b1 = MediaPlayer.create(this, R.raw.bmbm);

// Use OnBoomListenerAdapter to listen part of methods
        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here

            }
        });

        // Use OnBoomListener to listen all methods
        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                // If you have implement listeners for boom-buttons in builders,
                // then you shouldn't add any listener here for duplicate callbacks.
            }

            /**
             * When the background of boom-buttons is clicked.
             */
            @Override
            public void onBackgroundClick() {
                b1.start();

            }

            /**
             * When the BMB is going to hide its boom-buttons.
             */
            @Override
            public void onBoomWillHide() {
                b1.start();
            }

            /**
             * When the BMB finishes hide animations.
             */
            @Override
            public void onBoomDidHide() {

            }

            /**
             * When the BMB is going to show its boom-buttons.
             */
            @Override
            public void onBoomWillShow() {
                WifiUtils.withContext(getApplicationContext()).enableWifi(this::checkResult);

                b1.start();
            }

            private void checkResult(boolean isSuccess)
            {
                if (isSuccess)
                    new MaterialToast(context)
                        .setMessage(getResources().getString(R.string.wifion))
                        .setIcon(R.drawable.wifi)
                        .setDuration(Toast.LENGTH_LONG)
                        .setBackgroundColor(Color.parseColor("#55b859"))
                        .show();
                else
                    new MaterialToast(context)
                        .setMessage(getResources().getString(R.string.wifion))
                        .setIcon(R.drawable.nowifi)
                        .setDuration(Toast.LENGTH_LONG)
                        .setBackgroundColor(Color.parseColor("#EE4035"))
                        .show();
            }

            /**
             * When the BMB finished boom animations.
             */
            @Override
            public void onBoomDidShow() {

            }
        });

        backb = MediaPlayer.create(this, R.raw.backb);

        cycleMenuWidget = findViewById(R.id.itemCycleMenuWidget);
        cycleMenuWidget.setMenuRes(R.menu.menu_main);

        animation_view.setAnimation("final.json");
        animation_view.setRenderMode(RenderMode.SOFTWARE);
        animation_view.playAnimation();
        animation_view.loop(false);
        animation_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animation_view.isAnimating()){
                    animation_view.pauseAnimation();
                }else {
                    animation_view.resumeAnimation();
                }

            }
        });

        hTextView = findViewById(R.id.text2);
        menu = findViewById(R.id.menu);
        ff = findViewById(R.id.ff);
        new ClickShrinkEffect(btn_rec);
        new ClickShrinkEffect(menu);
        new ClickShrinkEffect(frst);
        new ClickShrinkEffect(tow);
        new ClickShrinkEffect(three);
        new ClickShrinkEffect(fan);
        new ClickShrinkEffect(four);

        if (context.getString(R.string.lang).equals(egyptLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.yad);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._26font_mdp));
            mCounter = 12;
            sentences = new String[]{"ﺓﺮﻘﺒﻟﺍ ﺓﺭﻮﺳ","ﻢﻴﺣﺮﻟﺍ ﻦﻤﺣﺮﻟﺍ ﻪﻠﻟﺍ ﻢﺴﺑ", "ﻚﻬﺟﻭ ﺐﻠﻘﺗ ﻯﺮﻧ ﺪﻗ",
                "ءﺎﻤﺴﻟﺍ ﻰﻓ", "ﺎﻫﺎﺿﺮﺗ ﺔﻠﺒﻗ ﻚﻨﻴﻟﻮﻨﻠﻓ", "ﻡﺍﺮﺤﻟﺍ ﺪﺠﺴﻤﻟﺍ ﺮﻄﺷ ﻚﻬﺟﻭ ﻝﻮﻓ", "ﻢﺘﻨﻛ ﺎﻣ ﺚﻴﺣﻭ",
                "ﻩﺮﻄﺷ ﻢﻜﻫﻮﺟﻭ ﺍﻮﻟﻮﻓ", "ﺏﺎﺘﻜﻟﺍ ﺍﻮﺗﻭﺃ ﻦﻳﺬﻟﺍ ﻥﺇﻭ", "ﻢﻬﺑﺭ ﻦﻣ ﻖﺤﻟﺍ ﻪﻧﺃ ﻥﻮﻤﻠﻌﻴﻟ",
                "ﻥﻮﻠﻤﻌﻳ ﺎﻤﻋ ﻞﻓﺎﻐﺑ ﻪﻠﻟﺍ ﺎﻣﻭ", "ﻢﻴﻈﻌﻟﺍ ﻪﻠﻟﺍ ﻕﺪﺻ","144 ﺔﻳﻵﺍ"};

            menu.setTypeface(FontManger.yad);

            ar = new GradientDrawable();
            ar.setColor(getResources().getColor(R.color.solid_bg_ovalar));
            ar.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(ar);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_add);
                btn_rec.setImageResource(R.drawable.btn_recar);
            }

            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/ar/browse/arabic_mokhtasar");
        }
        if(context.getString(R.string.lang).equals(chinaLanguageCode)){
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.asian);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 11;
            sentences = new String[]{"黄牛章","奉至仁至慈的真主之名", "我確已見你反復地仰視天空",
                "故我必使你轉向你所喜悅的朝向", "你應當把你的臉轉向禁寺", "你們無論在那裡", "都應當把你們的瞼轉向禁寺",
                "曾受天經者必定知道這是", "從他們的主降示的真理", "真主絕不忽視他們的行為",
                "诚信的大神","诗句 144"};

            menu.setTypeface(FontManger.asian);

            zh = new GradientDrawable();
            zh.setColor(getResources().getColor(R.color.solid_bg_ovalzh));
            zh.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(zh);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addzh);
                btn_rec.setImageResource(R.drawable.btn_reczh);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/zh/browse/chinese_makin");
        }
        if(context.getString(R.string.lang).equals(czechLanguageCode)){
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 19;
            sentences = new String[]{"Al-Baqarah(The Cow)", "Ve jménu Boha", "milosrdného,slitovného.",
                "Vídáme tě často", "obracet tvář svou k nebi", "sem a tam a obrátíme",
                "tě proto ke qible", " jež se ti bude líbit", "Obrať tedy tvář","svou směrem k Mešitě posvátné!",
                "A kdekoliv budete","obracejte tvář svou ve","směru jejím!","Věru ti, jimž dostalo se Písma",
                "vědí dobře, že to je pravda","od Pána jejich přicházející.","A není Bohu lhostejné",",co dělají.",
                "Věřte Bohu všemohoucímu","verš 144"};

            menu.setTypeface(FontManger.english);

            cs = new GradientDrawable();
            cs.setColor(getResources().getColor(R.color.solid_bg_ovalcs));
            cs.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(cs);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addcs);
                btn_rec.setImageResource(R.drawable.btn_reccs);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_19.html");
        }
        if(context.getString(R.string.lang).equals(holandLanguageCode)){
            hTextView.setTextColor(Color.BLACK);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 19;
            sentences = new String[]{"Al-Baqarah(The Cow)","In naam van den lankmoedigen", "en albarmhartigen God",
                "Wij hebben gezien, dat gij uw", "gezicht naar den Hemel wendt,", "maar wij willen het",
                "eene richting geven", "die u aangenaam is","Wendt daarom uw gezicht", "naar den heiligen tempel;",
                "waar gij u ook bevindt,","wendt uw aangezicht daarheen.","Zij die de schrift ontvingen,","weten het wel,",
                "dat deze waarheid","van hunnen Heer komt,","en God is niet onopmerkzaam","omtrent hunne daden.",
                "Geloof God de Almachtige","het Vers 144"};

            menu.setTypeface(FontManger.english);

            nl = new GradientDrawable();
            nl.setColor(getResources().getColor(R.color.solid_bg_ovalnl));
            nl.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(nl);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addnl);
                btn_rec.setImageResource(R.drawable.btn_recnl);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_5.html");
        }
        if(context.getString(R.string.lang).equals(londonLanguagwCode)){
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 23;
            sentences = new String[]{"Al-Baqarah(The Cow)","In the name of Allah,", "the Entirely Merciful,",
                "the Especially Merciful.", "We have certainly seen", " the turning of your face,",
                "[O Muhammad],","toward the heaven,","and We will surely turn you", "to a qiblah with which",
                "you will be pleased.","So turn your face","toward al-Masjid al-Haram.","And wherever you [believers] are,",
                "turn your faces","toward it [in prayer].","Indeed, those who have been","given the Scripture well know",
                "that it is the truth","from their Lord.","And Allah is not unaware","of what they do.","Great truth of God",
                "Verse 144"};

            menu.setTypeface(FontManger.english);

            en = new GradientDrawable();
            en.setColor(getResources().getColor(R.color.solid_bg_ovalen));
            en.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(en);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_adden);
                btn_rec.setImageResource(R.drawable.btn_recen);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/en/browse/english_saheeh");
        }
        if(context.getString(R.string.lang).equals(franceLanguageCode)){
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 20;
            sentences = new String[]{"La génisse(Al-Baqarah)","Au nom d'Allah,", "le Tout Miséricordieux,",
                "le Très Miséricordieux.", "Certes nous te voyons", "tourner le visage en tous", "sens dans le ciel.",
                "Nous te faisons donc orienter", "vers une direction qui te plaît.", "Tourne donc ton","visage vers la Mosquée sacrée.",
                "Où que vous soyez,","tournez-y vos visages.","Certes, ceux à qui le Livre","a été donné savent bien que","c'est la vérité venue","de leur Seigneur.",
                "Et Allah n'est pas inattentif","à ce qu'ils font","Crois Dieu tout puissant","verset 144"};

            menu.setTypeface(FontManger.english);

            fr = new GradientDrawable();
            fr.setColor(getResources().getColor(R.color.solid_bg_ovalfr));
            fr.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(fr);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addfr);
                btn_rec.setImageResource(R.drawable.btn_recfr);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/fr/browse/french_montada");
        }
        if(context.getString(R.string.lang).equals(germanyLanguageCode)){
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 26;
            sentences = new String[]{"al-Baqara(Die Kuh)","Im Namen Gottes,", "des Erbarmers,","des Barmherzigen.",
                "Wir sehen, wie du dein Gesicht", "zum Himmel hin","und her richtest.", "So werden Wir dir eine",
                "Gebetsrichtung festlegen,", "mit der du", "zufrieden sein wirst.","Wende also dein Gesicht",
                "in Richtung der","heiligen Moschee.","Und wo immer ihr seid,","wendet euer Gesicht","in ihre Richtung.",
                "Diejenigen, denen das Buch","zugekommen ist,","wissen bestimmt, daß","es die Wahrheit von"," ihrem Herrn ist.",
                "Gott läßt nicht unbeachtet,","was sie tun.","Glaube dem","allmächtigen Gott","Vers 144"};

            menu.setTypeface(FontManger.english);

            de = new GradientDrawable();
            de.setColor(getResources().getColor(R.color.solid_bg_ovalde));
            de.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(de);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addde);
                btn_rec.setImageResource(R.drawable.btn_recde);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/de/browse/german_bubenheim");
        }
        if (context.getString(R.string.lang).equals(indoniseLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 33;
            sentences = new String[]{"Surah Al-Baqarah(Lembu Betina)","Dengan menyebut nama", "Allah Yang Maha Pemurah",
                "lagi Maha Penyayang.", "Sungguh Kami(sering)", "melihat mukamu", "menengadah ke langit,",
                "maka sungguh Kami", "akan memalingkan", "kamu ke kiblat","yang kamu sukai.","Palingkanlah mukamu ke",
                "arah Masjidil Haram.","Dan dimana saja","kamu berada,","palingkanlah mukamu","ke arahnya.","Dan sesungguhnya",
                "orang-orang","(Yahudi dan Nasrani)","yang diberi Al Kitab","(Taurat dan Injil)","memang mengetahui,","bahwa berpaling",
                "ke Masjidil Haram","itu adalah benar","dari Tuhannya;","dan Allah","sekali-kali","tidak lengah","dari apa yang",
                "mereka kerjakan.","Believe God Almighty","ayat 144"};

            menu.setTypeface(FontManger.english);

            in = new GradientDrawable();
            in.setColor(getResources().getColor(R.color.solid_bg_ovalin));
            in.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(in);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addin);
                btn_rec.setImageResource(R.drawable.btn_recin);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/id/browse/indonesian_sabiq");
        }

        if (context.getString(R.string.lang).equals(italyLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 25;
            sentences = new String[]{"Al-Baqarah(The Cow)","In nome di Allah,", "il Compassionevole,",
                "il Misericordioso", "Ti abbiamo visto", "volgere il viso,", "al cielo. Ebbene,", "ti daremo",
                "un orientamento", "che ti piacerà.","Volgiti dunque verso","la Sacra Moschea.","Ovunque siate,",
                "rivolgete il volto","nella sua direzione.","Certo, coloro","a cui è stato","dato il Libro,","sanno che questa è",
                "la verità che viene","dal loro Signore.","Allah non","è incurante di","quello che fate.",
                "Credi a Dio Onnipotente","versetto 144"};

            menu.setTypeface(FontManger.english);

            it = new GradientDrawable();
            it.setColor(getResources().getColor(R.color.solid_bg_ovalit));
            it.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(it);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addit);
                btn_rec.setImageResource(R.drawable.btn_recit);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/en/browse/italian_rwwad");
        }
        if (context.getString(R.string.lang).equals(japanLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.asian);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 21;
            sentences = new String[]{"Al-Baqarah(The Cow)","慈悲あまねく慈愛深き", "アッラーの御名において.",
                "われはあなたが(導きを求め),", "天に顔を巡らすのを見る.", "そこでわれは,", "あなたの納得するキブラに,",
                "あなたを向かわせる.", "あなたの顔を聖な", "るマスジドの方","向に向けなさい.","あなたがたは何処にいても,",
                "あなたがたの顔を","キブラに向けなさい.","本当に啓典の民は,","それが主からの真理で","あることを知っている.",
                "アッラーは,","かれらの行うことに","無頓着な方ではない.","全能の神を信じる","韻文 144"};

            menu.setTypeface(FontManger.asian);

            ja = new GradientDrawable();
            ja.setColor(getResources().getColor(R.color.solid_bg_ovalja));
            ja.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(ja);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addja);
                btn_rec.setImageResource(R.drawable.btn_recja);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/en/browse/japanese_saeedsato");
        }
        if (context.getString(R.string.lang).equals(koreaLanguageCode)) {
            hTextView.setTextColor(Color.BLACK);
            hTextView.setTypeface(FontManger.asian);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 20;
            sentences = new String[]{"알-바까라(암소)장","자비로우시고 자애로우신", "하나님의 이름으로",
                "하나님께서 하늘을", "향한 그 대의 얼굴을 보", "고 있었노라 그대 가", "원하는 기도의 방향",
                "을 향하게 하리라 그대", "의 고개를 영원한 경", "배의 장소로 향하라","어디에 있든 그 쪽으로","고개를 향할지니 성",
                "서 를 계시받은 이들","은 이 계시가 그 들의","주님으로부터 온 진","실이라는 것을 알고","있으며 하나님은 그들",
                "이 하는것에 대해 모","르는 분이 아 니시라", "전능하신 하나님을 믿으 라.","절 144"};

            menu.setTypeface(FontManger.asian);

            ko = new GradientDrawable();
            ko.setColor(getResources().getColor(R.color.solid_bg_ovalko));
            ko.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(ko);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addko);
                btn_rec.setImageResource(R.drawable.btn_recko);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/en/browse/korean_hamid");
        }
        if (context.getString(R.string.lang).equals(iranLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.yad);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._26font_mdp));
            mCounter = 12;
            sentences = new String[]{"ﺓﺮﻘﺒﻟﺍ ﺓﺭﻮﺳ","ﻢﻴﺣﺮﻟﺍ ﻦﻤﺣﺮﻟﺍ ﻪﻠﻟﺍ ﻢﺴﺑ", "ﻚﻬﺟﻭ ﺐﻠﻘﺗ ﻯﺮﻧ ﺪﻗ",
                "ءﺎﻤﺴﻟﺍ ﻰﻓ", "ﺎﻫﺎﺿﺮﺗ ﺔﻠﺒﻗ ﻚﻨﻴﻟﻮﻨﻠﻓ", "ﻡﺍﺮﺤﻟﺍ ﺪﺠﺴﻤﻟﺍ ﺮﻄﺷ ﻚﻬﺟﻭ ﻝﻮﻓ", "ﻢﺘﻨﻛ ﺎﻣ ﺚﻴﺣﻭ",
                "ﻩﺮﻄﺷ ﻢﻜﻫﻮﺟﻭ ﺍﻮﻟﻮﻓ", "ﺏﺎﺘﻜﻟﺍ ﺍﻮﺗﻭﺃ ﻦﻳﺬﻟﺍ ﻥﺇﻭ", "ﻢﻬﺑﺭ ﻦﻣ ﻖﺤﻟﺍ ﻪﻧﺃ ﻥﻮﻤﻠﻌﻴﻟ",
                "ﻥﻮﻠﻤﻌﻳ ﺎﻤﻋ ﻞﻓﺎﻐﺑ ﻪﻠﻟﺍ ﺎﻣﻭ", "ﻢﻴﻈﻌﻟﺍ ﻪﻠﻟﺍ ﻕﺪﺻ","144 ﺔﻳﻵﺍ"};

            menu.setTypeface(FontManger.yad);

            fa = new GradientDrawable();
            fa.setColor(getResources().getColor(R.color.solid_bg_ovalfa));
            fa.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(fa);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addfa);
                btn_rec.setImageResource(R.drawable.btn_recfa);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/fa/browse/persian_ih");
        }
        if (context.getString(R.string.lang).equals(polandLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 28;
            sentences = new String[]{"Al-Baqarah(The Cow)","W Imię Boga Miłosiernego", "i Litościwego!",
                "Widzieliśmy zwrócenie", "twojej twarzy", "ku niebu,", "My zaś teraz", "chcemy zwrócić",
                "się w kierunku,", "który ciebie zadowoli.","Zwróć więc twoją","twarz w kierunku","świętego Meczetu.",
                "I gdziekolwiek","się znajdziecie,","zwracajcie się twarzami","w jego kierunku.","Zaprawdę, ci,","którym została",
                "dana Księga,","wiedzą, że to","jest prawda","pochodząca od","ich Pana.","A Bóg nie","lekceważy tego,","co czynicie!",
                "Wielka prawda o Bogu","Wiersze 144"};

            menu.setTypeface(FontManger.english);

            pl = new GradientDrawable();
            pl.setColor(getResources().getColor(R.color.solid_bg_ovalpl));
            pl.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(pl);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addpl);
                btn_rec.setImageResource(R.drawable.btn_recpl);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_32.html");
        }
        if (context.getString(R.string.lang).equals(protogalLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 22;
            sentences = new String[]{"A Vaca","Em nome de Deus,", "o Clemente, o Misericordioso.",
                "Vimos-te (ó Mensageiro)", "orientar o rosto", "para o céu; portanto,", "orientar-te-emos até",
                "a quibla que te satisfaça.", "Orientateu rosto", "(ao cumprir a oração)","para a Sagrada Mesquita",
                "(de Makka)!","E vós (crentes),","onde quer que vos encontreis,","orientaivossos rosto até ela.",
                "Aqueles que receberam o Livro,","bem sabem que","isto é a verdade","de seu Senhor;","e Deus não",
                "estádesatento a quanto fazem.","(Acredite) o grande Deus","verso 144"};

            menu.setTypeface(FontManger.english);

            pt = new GradientDrawable();
            pt.setColor(getResources().getColor(R.color.solid_bg_ovalpt));
            pt.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(pt);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addpt);
                btn_rec.setImageResource(R.drawable.btn_recpt);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/pt/browse/portuguese_nasr");
        }
        if (context.getString(R.string.lang).equals(romaniaLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 19;
            sentences = new String[]{"Al-Baqarah(The Cow)","În numele lui Dumnezeu", "cel Milos şi Milostiv.",
                "Te-am văzut că ţi-ai întors", "faţa către cer ca să-ţi", " dăm qibla care să-ţi", "fie pe plac.",
                "Întoarce-ţi faţa către", "Moscheea cea Sfântă!", "Oriunde aţi fi,","întoarceţi-vă faţa către ea!",
                "Cei care au primit Cartea,","ştiu că acesta este","Adevărul de la Domnul lor.","Dumnezeu nu este",
                "nepăsător la ceea","ce făptuiesc ei.","Crede-l pe","Dumnezeu Atotputernic","vers 144"};

            menu.setTypeface(FontManger.english);

            ro = new GradientDrawable();
            ro.setColor(getResources().getColor(R.color.solid_bg_ovalro));
            ro.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(ro);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addro);
                btn_rec.setImageResource(R.drawable.btn_recro);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_22.html");
        }
        if (context.getString(R.string.lang).equals(russiaLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.russi);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 28;
            sentences = new String[]{"Корова","Во имя Аллаха,","Милостивого,", "Милосердного!","Мы видели,",
                "как ты обращал", "свое лицо к небу,", "и Мы обратим","тебя к кибле,","которой ты",
                "останешься доволен.","Обрати же свое","лицо в сторону","Заповедной мечети.","Где бы вы ни были,",
                "обращайте ваши","лица в ее сторону.","Воистину, те,","которым даровано","Писание, знают,","что такова истина",
                "от их Господа.","Аллах не пребывает","в неведении","относительно того,","что они совершают.","Верь Богу",
                "Всемогущему","стихе 144"};

            menu.setTypeface(FontManger.russi);

            ru = new GradientDrawable();
            ru.setColor(getResources().getColor(R.color.solid_bg_ovalru));
            ru.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(ru);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addru);
                btn_rec.setImageResource(R.drawable.btn_recru);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_23.html");
        }
        if (context.getString(R.string.lang).equals(spianLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 20;
            sentences = new String[]{"La Vaca","¡En el nombre de Alá,", "el Compasivo, el Misericordioso!",
                "Vemos cómo vuelves", "tu rostro al cielo.", "Haremos, pues,", "que te vuelvas hacia una","dirección que te satisfaga.",
                "Vuelve tu rostro hacia","la Mezquita Sagrada.","Dondequiera que estéis,","volved vuestro","rostro hacia ella.",
                "Aquéllos que han recibido","la Escritura saben bien","que es la Verdad que","viene de su Señor.","Alá está atento",
                "a lo que hacen.","(Cree)el gran dios","verso 144"};

            menu.setTypeface(FontManger.english);

            es = new GradientDrawable();
            es.setColor(getResources().getColor(R.color.solid_bg_ovales));
            es.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(es);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addes);
                btn_rec.setImageResource(R.drawable.btn_reces);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/es/browse/spanish_garcia");
        }
        if (context.getString(R.string.lang).equals(thalandLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 26;
            sentences = new String[]{"อัล-บะเกาะเราะฮ","ด้วยพระนามของอัลลอฮฺ","ผู้ทรงกรุณาปราณี","ผู้ทรงเมตตาเสมอ",
                "แท้จริงเราเห็นใบหน้า", "ของเจ้าแหงนไป", "ในฟากฟ้าบ่อยครั้ง","แน่นอนเราให้เ","จ้าผินไปยังทิศ","ที่เจ้าพึงใจ",
                "ดังนั้นเจ้าจงผินใบหน้","าของเจ้าไปทางมั","สยิดิลฮะรอมเถิด","และที่ใดก็ตามที่พ","วกเจ้าปรากฏอยู่","ก็จงผินใบหน้าของพ",
                "วกเจ้าไปทางทิศนั้น","และแท้จริงบรรด","าผู้ที่ได้รับคัมภีร์","นั้นย่อมรู้ดีว่ามัน","คือความจริงที่มาจากพ","ระเจ้าของพวกเขา",
                "และอัลลอฮ์นั้นไม่ท","รงเผลอในสิ่งที่พ","วกเขากระทำกัน","(เชื่อ)พระเจ้าผู้ยิ่งใหญ่","กลอน 144"};

            menu.setTypeface(FontManger.english);

            th = new GradientDrawable();
            th.setColor(getResources().getColor(R.color.solid_bg_ovalth));
            th.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(th);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addth);
                btn_rec.setImageResource(R.drawable.btn_recth);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_37.html");
        }
        if (context.getString(R.string.lang).equals(turkyLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 27;
            sentences = new String[]{"Bakara Suresi","Rahmân ve Rahîm olan", "Allah'in ismiyle.","Dogrusu, biz,",
                "yüzünün semaya yöneldigini,","orada sekilden sekile geçerek,","aranip durdugunu görüyorduk.",
                "Artik seni hosnud olacagin", "bir kibleye çevirecegiz.","Haydi bakalim,","yüzünü Mescid-i Haram'a",
                "dogru çevir.","Siz de ey müminler,","nerede olursaniz olun,","yüzünüzü o tarafa","dogru çevirin!",
                "Kendilerine kitap","verilmis olanlar da","kesinlikle bilirler ki,","Rabblerinden gelen","o emir haktir.",
                "Ve Allah,","onlarin yaptiklarindan","ve yapmakta","olduklarindan","gafil degildir.",
                "Allah doğru söyledi","ayet 144"};

            menu.setTypeface(FontManger.english);

            tr = new GradientDrawable();
            tr.setColor(getResources().getColor(R.color.solid_bg_ovaltr));
            tr.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(tr);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addtr);
                btn_rec.setImageResource(R.drawable.btn_rectr);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/en/browse/turkish_rwwad");
        }
        if (context.getString(R.string.lang).equals(malyseLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 28;
            sentences = new String[]{"Surah Al-Baqarah(Lembu Betina)","Kerap kali Kami melihat"," engkau (wahai Muhammad),",
                    "berulang-ulang menengadah","ke langit, maka Kami","benarkan engkau berpaling","mengadap kiblat",
                    " yang engkau sukai.","Oleh itu palingkanlah","mukamu ke arah","masjid Al-Haraam ","(tempat letaknya Kaabah);",
                    "dan di mana sahaja","kamu berada maka","hadapkanlah muka","kamu ke arahnya.","Dan sesungguhnya orang",
                    "-orang (Yahudi dan Nasrani)","yang telah","diberikan Kitab,","mereka mengetahui bahawa","perkara (berkiblat ke Kaabah)",
                    "itu adalah perintah","yang benar dari","Tuhan mereka;","dan Allah tidak","sekali-kali lalai akan","apa yang mereka lakukan.","ayat 144"};

            menu.setTypeface(FontManger.english);

            ms = new GradientDrawable();
            ms.setColor(getResources().getColor(R.color.solid_bg_ovalen));
            ms.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(ms);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_adden);
                btn_rec.setImageResource(R.drawable.btn_recen);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_7.html");
        }
        if (context.getString(R.string.lang).equals(indiaLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 23;
            sentences = new String[]{"Al-Baqarah(The Cow)","In the name of Allah,", "the Entirely Merciful,",
                    "the Especially Merciful.", "We have certainly seen", " the turning of your face,",
                    "[O Muhammad],","toward the heaven,","and We will surely turn you", "to a qiblah with which",
                    "you will be pleased.","So turn your face","toward al-Masjid al-Haram.","And wherever you [believers] are,",
                    "turn your faces","toward it [in prayer].","Indeed, those who have been","given the Scripture well know",
                    "that it is the truth","from their Lord.","And Allah is not unaware","of what they do.","Great truth of God",
                    "Verse 144"};

            menu.setTypeface(FontManger.english);

            hi = new GradientDrawable();
            hi.setColor(getResources().getColor(R.color.ld_textInputError));
            hi.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(hi);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addfa);
                btn_rec.setImageResource(R.drawable.btn_recfa);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/hi/browse/hindi_omari");
        }

        if (context.getString(R.string.lang).equals(bngalideshLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 23;
            sentences = new String[]{"Al-Baqarah(The Cow)","In the name of Allah,", "the Entirely Merciful,",
                    "the Especially Merciful.", "We have certainly seen", " the turning of your face,",
                    "[O Muhammad],","toward the heaven,","and We will surely turn you", "to a qiblah with which",
                    "you will be pleased.","So turn your face","toward al-Masjid al-Haram.","And wherever you [believers] are,",
                    "turn your faces","toward it [in prayer].","Indeed, those who have been","given the Scripture well know",
                    "that it is the truth","from their Lord.","And Allah is not unaware","of what they do.","Great truth of God",
                    "Verse 144"};

            menu.setTypeface(FontManger.english);

            bn = new GradientDrawable();
            bn.setColor(getResources().getColor(R.color.solid_bg_ovalit));
            bn.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(bn);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addit);
                btn_rec.setImageResource(R.drawable.btn_recit);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/bn/browse/bengali_zakaria");
        }

        if (context.getString(R.string.lang).equals(pakistanLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.yad);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._26font_mdp));
            mCounter = 12;
            sentences = new String[]{"ﺓﺮﻘﺒﻟﺍ ﺓﺭﻮﺳ","ﻢﻴﺣﺮﻟﺍ ﻦﻤﺣﺮﻟﺍ ﻪﻠﻟﺍ ﻢﺴﺑ", "ﻚﻬﺟﻭ ﺐﻠﻘﺗ ﻯﺮﻧ ﺪﻗ",
                    "ءﺎﻤﺴﻟﺍ ﻰﻓ", "ﺎﻫﺎﺿﺮﺗ ﺔﻠﺒﻗ ﻚﻨﻴﻟﻮﻨﻠﻓ", "ﻡﺍﺮﺤﻟﺍ ﺪﺠﺴﻤﻟﺍ ﺮﻄﺷ ﻚﻬﺟﻭ ﻝﻮﻓ", "ﻢﺘﻨﻛ ﺎﻣ ﺚﻴﺣﻭ",
                    "ﻩﺮﻄﺷ ﻢﻜﻫﻮﺟﻭ ﺍﻮﻟﻮﻓ", "ﺏﺎﺘﻜﻟﺍ ﺍﻮﺗﻭﺃ ﻦﻳﺬﻟﺍ ﻥﺇﻭ", "ﻢﻬﺑﺭ ﻦﻣ ﻖﺤﻟﺍ ﻪﻧﺃ ﻥﻮﻤﻠﻌﻴﻟ",
                    "ﻥﻮﻠﻤﻌﻳ ﺎﻤﻋ ﻞﻓﺎﻐﺑ ﻪﻠﻟﺍ ﺎﻣﻭ", "ﻢﻴﻈﻌﻟﺍ ﻪﻠﻟﺍ ﻕﺪﺻ","144 ﺔﻳﻵﺍ"};

            menu.setTypeface(FontManger.yad);

            ur = new GradientDrawable();
            ur.setColor(getResources().getColor(R.color.solid_bg_ovalit));
            ur.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(ur);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addit);
                btn_rec.setImageResource(R.drawable.btn_recit);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/ur/browse/urdu_junagarhi");
        }

        if (context.getString(R.string.lang).equals(swideshLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 25;
            sentences = new String[]{"Al-Baqarah(The Cow)", "I GUDS, DEN NÅDERIKES", "DEN BARMHÄRTIGES NAMN",
                    "Ofta har Vi", "sett dig [Muhammad]", "vända ansiktet mot", "himlen [i väntan på ledning],", "och Vi skall låta dig",
                    "vända dig i en", "böneriktning som skall", "göra dig nöjd.", "Vänd alltså nu", "ditt ansikte mot", "den heliga Moskén;",
                    " ja, var ni än befinner", "er, vänd era ansikten", "mot denna [Moské].", "De som [förr] fick", "ta emot uppenbarelsen",
                    "vet säkert att detta", "är ett sant [påbud]", "från deras Herre.", "Och Gud", "förbiser ingenting", "av vad de gör.",
                    "Vers 144"};

            menu.setTypeface(FontManger.english);

            sv = new GradientDrawable();
            sv.setColor(getResources().getColor(R.color.about_facebook_color));
            sv.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(sv);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addtr);
                btn_rec.setImageResource(R.drawable.btn_reces);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_24.html");
        }

        if (context.getString(R.string.lang).equals(albaniLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 22;
            sentences = new String[]{"Al-Baqarah ( The Cow )","Ne shumë herë po", "shohim kthimin e",
                    "fytyrës tënde kah qielli,", "e Ne gjithqysh do të", "drejtojmë ty në drejtim", "të një kibleje (Qabja)",
                    "që ti e do atë.", "Pra kthehu anës", "së xhamisë së","shenjtë (Qabes),","he kudo që të",
                    "jeni (o besimtarë)","kthehuni kah ajo anë.","E atyre që u","është dhuruar libri,","ata e dinë sigurisht se",
                    "kjo (kthesë) është e","vërtetë nga Zoti i tyre.","E All-llahut nuk","mund t’i fshihet ajo","që veprojnë ata.",
            "vargj {144}"};

            menu.setTypeface(FontManger.english);

            sq = new GradientDrawable();
            sq.setColor(getResources().getColor(R.color.solid_bg_ovalja));
            sq.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(sq);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addja);
                btn_rec.setImageResource(R.drawable.btn_recja);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/sq/browse/albanian_nahi");
        }

        if(context.getString(R.string.lang).equals(azrabiganLanguageCode)){
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 19;
            sentences = new String[]{"Al-Baqarah (The Cow)","Mərhəmətli, rəhmli", "Mərhəmətli, rəhmli","(Ya Rəsulum!) Biz",
                    "sənin üzünün göyə", "tərəf çevrildiyini görürük,","ona görə də səni", "razı olduğun qibləyə",
                    "tərəf döndərəcəyik.", "İndi üzünü Məscidülhərama", "tərəf çevir! (Ey müsəlmanlar!)","Harada olsanız (namaz vaxtı)",
                    "üzünüzü oraya döndərin!","Kitab verilmişlər bunun","öz Rəbbi tərəfindən","bir həqiqət olduğunu","yaxşı bilirlər.",
                    "Allah onların əməllərindən","xəbərsiz deyildir.", "Ayə 144"};

            menu.setTypeface(FontManger.english);

            az = new GradientDrawable();
            az.setColor(getResources().getColor(R.color.solid_bg_ovalit));
            az.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(az);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addde);
                btn_rec.setImageResource(R.drawable.btn_recro);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/en/browse/azeri_musayev");
        }
        if(context.getString(R.string.lang).equals(bosnaLanguageCode)){
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 21;
            sentences = new String[]{"Al-Baqarah (The Cow)","U ime Allaha,", "Milostivog, Samilosnog!","Vidimo Mi kako sa",
                    "žudnjom bacaš", "pogled prema nebu,","i Mi ćemo sigurno", "učiniti da se okrećeš", "prema strani koju",
                    "ti želiš:", "okreni zato lice","svoje prema Časnome", "hramu! I ma gdje bili,","okrenite lica svoja","na tu stranu.",
                    "Oni kojima je data","Knjiga sigurno znaju da", "je to istina od","Gospodara njihova –","a Allah motri na",
                    "ono što oni rade.", "Stih 144"};

            menu.setTypeface(FontManger.english);

            bs = new GradientDrawable();
            bs.setColor(getResources().getColor(R.color.solid_bg_ovalde));
            bs.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(bs);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addcs);
                btn_rec.setImageResource(R.drawable.btn_recen);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/bs/browse/bosnian_rwwad");
        }
        if (context.getString(R.string.lang).equals(bulgaryLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.russi);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 25;
            sentences = new String[]{"В името на Аллах,","Всемилостивия,","Милосърдния", "Виждаме да се","обръща твоето лице",
                    "[о, Мухаммад]", "към небето.", "Ние непременно","ще те обърнем в","посока за молитви,", "която ще",
                    "ти е угодна.","Тъй че обърни","лице към","Свещената джамия!", "Където и да бъдете,","обръщайте",
                    "лице към нея!","Дарените с","Писанието знаят,","че това е истината","от техния Господ.","Аллах не подминава",
                    "техните дела.","Голяма истина на Бога","Стих 144"};

            menu.setTypeface(FontManger.russi);

            bg = new GradientDrawable();
            bg.setColor(getResources().getColor(R.color.solid_bg_ovaltr));
            bg.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(bg);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addja);
                btn_rec.setImageResource(R.drawable.btn_recfa);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_28.html");
        }
        if (context.getString(R.string.lang).equals(nigeraLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 26;
            sentences = new String[]{"Sorah Al-Baqarah (The Cow)","Da sũnan Allah,", "Mai rahama, Mai jin ƙai.",
                    "Lalle ne, Muna", "ganin jujjũyãwar", "fuskarka a cikin sama.", "To, lalle ne, Mu jũyar",
                    "da kai ga Alƙibla wadda", "kake yardã da ita.", "Sai ka jũyar da","fuskarka wajen","Masallãci Tsararre,",
                    "kuma inda duk","kuka kasance, to,","ku jũyar da","fuskõkinku jiharsa.","Kuma lalle ne","waɗanda aka bai wa",
                    "Littãfi, haƙĩƙa su, sunã","sanin lalle ne,","shĩ ne gaskiya,","daga Ubangijinka.","Kuma Allah bai"," zama Mai gafala",
                    "ba daga abin","da suke aikatãwa.","Aya ta 144"};

            menu.setTypeface(FontManger.english);

            ha = new GradientDrawable();
            ha.setColor(getResources().getColor(R.color.solid_bg_ovalin));
            ha.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(ha);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addin);
                btn_rec.setImageResource(R.drawable.btn_recin);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/en/browse/hausa_gummi");
        }
        if (context.getString(R.string.lang).equals(norwegLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._18font_mdp));
            mCounter = 21;
            sentences = new String[]{"Sorah Al-Baqarah(The Cow)","I Guds, den Barmhjertiges,", "den Nåderikes navn","Vi ser at du",
                    "vender ansiktet","søkende på himmelen.","Vi vil vende deg", "mot en bønneretning du", "vil bli tilfreds med.",
                    "Vend ditt ansikt mot","den hellige moské!", "Hvor dere enn er,","så vend deres","ansikter mot den.",
                    "De som har","mottatt skriften,", "vil visselig vite at","det er sannheten","fra deres Herre.","Gud overser ikke",
                    "hva de gjør.", "Vers 144"};

            menu.setTypeface(FontManger.english);

            no = new GradientDrawable();
            no.setColor(getResources().getColor(R.color.solid_bg_ovaltr));
            no.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(no);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addes);
                btn_rec.setImageResource(R.drawable.btn_rectr);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://www.omaniyat.net/quran/language_31.html");
        }
        if (context.getString(R.string.lang).equals(somailyLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 21;
            sentences = new String[]{"Suuradda Al-Baqarah (The Cow)","Magaca Eebe yaan", "kubillaabaynaa ee Naxariis",
                    "guud iyo mid", "gaaraba Naxariista.", "dhabahaan yaan ugu", "jeednay gagadookna",
                    "Wajigaaga ee Samada,", "waxaana kuu jeedinaynaa", "Qiblad aad ka raali noqon,","ee u jeedi Wajigaaga","xagga Masjidka Xurmeysan,",
                    "meelkastood joogtana u","jeediya Wajigiinna xagiisa,","kuwa Kitaabka la","siiyeyna waa ogyihiin","in jeedintaas","xaq Eebe tahay,",
                    "Eebana ma","halmaansana waxay","Camal fali.","Aayadda 144"};

            menu.setTypeface(FontManger.english);

            so = new GradientDrawable();
            so.setColor(getResources().getColor(R.color.about_twitter_color));
            so.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(so);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addfa);
                btn_rec.setImageResource(R.drawable.btn_recfr);
            }
          EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/en/browse/somali_abduh");
        }
        if (context.getString(R.string.lang).equals(kenyaLanguageCode)) {
            hTextView.setTextColor(Color.WHITE);
            hTextView.setTypeface(FontManger.english);
            hTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen._16font_mdp));
            mCounter = 25;
            sentences = new String[]{"Sorah Al-Baqarah (The Cow)","KWA JINA LA MWENYEZI", "MUNGU MWINGI WA",
                    "REHEMA MWENYE KUREHEMU.", "Kwa yakini tukiona", "unavyo geuza geuza", "uso wako mbinguni.",
                    "Basi tutakuelekeza kwenye", "Kibla ukipendacho.", "Basi elekeza uso","wako upande wa","Msikiti Mtakatifu;",
                    "na popote mnapokuwa","zielekezeni nyuso","zenu upande huo;","na hakika wale","walio pewa Kitabu","wanajua kwamba",
                    "hiyo ni haki itokayo","kwa Mola","wao Mlezi.","Na Mwenyezi Mungu","si mwenye kughafilika","na yale wanayo yatenda.",
                   "Kubwa kweli ya Mungu","Aya ya 144"};

            menu.setTypeface(FontManger.english);

            sw = new GradientDrawable();
            sw.setColor(getResources().getColor(R.color.black));
            sw.setCornerRadius(R.dimen._28mdp);
            ff.setBackground(sw);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cycleMenuWidget.setCornerImageResource(R.drawable.ic_addit);
                btn_rec.setImageResource(R.drawable.btn_recar);
            }
            EasySharedPreference.Companion.putString(KEY_WEB, "https://quranenc.com/en/browse/swahili_abubakr");
        }


        cycleMenuWidget.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View view, int itemPosition) {
                switch (itemPosition) {
                    case 0:

                        finalpop.start();
                        setLanguage("ar", "EG");
                        LocaleHelper.setLocale(MainActivity.this,egyptLanguageCode);
                        finish();
                        Intent f1 = new Intent(MainActivity.this, MainActivity.class);
                        f1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f1);
                        MaterialToast.makeText(context, "العربية", R.drawable.egypt, Toast.LENGTH_LONG).show();
                        break;
                    case 1:

                        finalpop.start();
                        setLanguage("zh", "CN");
                        LocaleHelper.setLocale(MainActivity.this,chinaLanguageCode);
                        finish();
                        Intent f2 = new Intent(MainActivity.this, MainActivity.class);
                        f2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f2);
                        MaterialToast.makeText(context, "中文", R.drawable.china, Toast.LENGTH_LONG).show();

                        break;
                    case 2:

                        finalpop.start();
                        setLanguage("cs", "CZ");
                        LocaleHelper.setLocale(MainActivity.this,czechLanguageCode);
                        finish();
                        Intent f3 = new Intent(MainActivity.this, MainActivity.class);
                        f3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f3);
                        MaterialToast.makeText(context, "česky", R.drawable.czech, Toast.LENGTH_LONG).show();
                        break;
                    case 3:

                        finalpop.start();
                        setLanguage("nl", "NL");
                        LocaleHelper.setLocale(MainActivity.this,holandLanguageCode);
                        finish();
                        Intent f4 = new Intent(MainActivity.this, MainActivity.class);
                        f4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f4);
                        MaterialToast.makeText(context, "nederlands", R.drawable.holand, Toast.LENGTH_LONG).show();
                        break;
                    case 4:

                        finalpop.start();
                        setLanguage("en", "GB");
                        LocaleHelper.setLocale(MainActivity.this,londonLanguagwCode);
                        finish();
                        Intent f5 = new Intent(MainActivity.this, MainActivity.class);
                        f5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f5);
                        MaterialToast.makeText(context, "english", R.drawable.london, Toast.LENGTH_LONG).show();
                        break;
                    case 5:

                        finalpop.start();
                        setLanguage("fr", "FR");
                        LocaleHelper.setLocale(MainActivity.this,franceLanguageCode);
                        finish();
                        Intent f6 = new Intent(MainActivity.this, MainActivity.class);
                        f6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f6);
                        MaterialToast.makeText(context, "français", R.drawable.france, Toast.LENGTH_LONG).show();
                        break;
                    case 6:

                        finalpop.start();
                        setLanguage("de", "DE");
                        LocaleHelper.setLocale(MainActivity.this,germanyLanguageCode);
                        finish();
                        Intent f7 = new Intent(MainActivity.this, MainActivity.class);
                        f7.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f7);
                        MaterialToast.makeText(context, "deutsche", R.drawable.germany, Toast.LENGTH_LONG).show();
                        break;
                    case 7:

                        finalpop.start();
                        setLanguage("in", "ID");
                        LocaleHelper.setLocale(MainActivity.this,indoniseLanguageCode);
                        finish();
                        Intent f8 = new Intent(MainActivity.this, MainActivity.class);
                        f8.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f8);
                        MaterialToast.makeText(context, "bahasa Indonesia", R.drawable.indonesia, Toast.LENGTH_LONG).show();
                        break;
                    case 8:

                        finalpop.start();
                        setLanguage("it", "IT");
                        LocaleHelper.setLocale(MainActivity.this,italyLanguageCode);
                        finish();
                        Intent f9 = new Intent(MainActivity.this, MainActivity.class);
                        f9.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f9);
                        MaterialToast.makeText(context, "italianosia", R.drawable.italy, Toast.LENGTH_LONG).show();
                        break;
                    case 9:

                        finalpop.start();
                        setLanguage("ja", "JA");
                        LocaleHelper.setLocale(MainActivity.this,japanLanguageCode);
                        finish();
                        Intent f10 = new Intent(MainActivity.this, MainActivity.class);
                        f10.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f10);
                        MaterialToast.makeText(context, "日本語", R.drawable.japan, Toast.LENGTH_LONG).show();
                        break;
                    case 10:

                        finalpop.start();
                        setLanguage("ko", "KR");
                        LocaleHelper.setLocale(MainActivity.this,koreaLanguageCode);
                        finish();
                        Intent f11 = new Intent(MainActivity.this, MainActivity.class);
                        f11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f11);
                        MaterialToast.makeText(context, "한국어", R.drawable.korea, Toast.LENGTH_LONG).show();
                        break;
                    case 11:

                        finalpop.start();
                        setLanguage("fa", "IR");
                        LocaleHelper.setLocale(MainActivity.this,iranLanguageCode);
                        finish();
                        Intent f12 = new Intent(MainActivity.this, MainActivity.class);
                        f12.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f12);
                        MaterialToast.makeText(context, "فارسی", R.drawable.iran, Toast.LENGTH_LONG).show();
                        break;
                    case 12:

                        finalpop.start();
                        setLanguage("pl", "PL");
                        LocaleHelper.setLocale(MainActivity.this,polandLanguageCode);
                        finish();
                        Intent f13 = new Intent(MainActivity.this, MainActivity.class);
                        f13.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f13);
                        MaterialToast.makeText(context, "polskie", R.drawable.poland, Toast.LENGTH_LONG).show();
                        break;
                    case 13:

                        finalpop.start();
                        setLanguage("pt", "PT");
                        LocaleHelper.setLocale(MainActivity.this,protogalLanguageCode);
                        finish();
                        Intent f14 = new Intent(MainActivity.this, MainActivity.class);
                        f14.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f14);
                        MaterialToast.makeText(context, "português", R.drawable.portugal, Toast.LENGTH_LONG).show();
                        break;
                    case 14:

                        finalpop.start();
                        setLanguage("ro", "RO");
                        LocaleHelper.setLocale(MainActivity.this,romaniaLanguageCode);
                        finish();
                        Intent f15 = new Intent(MainActivity.this, MainActivity.class);
                        f15.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f15);
                        MaterialToast.makeText(context, "română", R.drawable.romania, Toast.LENGTH_LONG).show();
                        break;
                    case 15:

                        finalpop.start();
                        setLanguage("ru", "RU");
                        LocaleHelper.setLocale(MainActivity.this,russiaLanguageCode);
                        finish();
                        Intent f16 = new Intent(MainActivity.this, MainActivity.class);
                        f16.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f16);
                        MaterialToast.makeText(context, "русский", R.drawable.russia, Toast.LENGTH_LONG).show();
                        break;
                    case 16:

                        finalpop.start();
                        setLanguage("es", "ES");
                        LocaleHelper.setLocale(MainActivity.this,spianLanguageCode);
                        finish();
                        Intent f17 = new Intent(MainActivity.this, MainActivity.class);
                        f17.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f17);
                        MaterialToast.makeText(context, "Español", R.drawable.spain, Toast.LENGTH_LONG).show();
                        break;
                    case 17:

                        finalpop.start();
                        setLanguage("th", "TH");
                        LocaleHelper.setLocale(MainActivity.this,thalandLanguageCode);
                        finish();
                        Intent f18 = new Intent(MainActivity.this, MainActivity.class);
                        f18.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f18);
                        MaterialToast.makeText(context, "ไทย", R.drawable.thailand, Toast.LENGTH_LONG).show();
                        break;
                    case 18:

                        finalpop.start();
                        setLanguage("tr","TR");
                        LocaleHelper.setLocale(MainActivity.this,turkyLanguageCode);
                        finish();
                        Intent f19 = new Intent(MainActivity.this, MainActivity.class);
                        f19.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f19);
                        MaterialToast.makeText(context, "Türk", R.drawable.turkey, Toast.LENGTH_LONG).show();
                        break;
                    case 19:

                        finalpop.start();
                        setLanguage("ms","MY");
                        LocaleHelper.setLocale(MainActivity.this,malyseLanguageCode);
                        finish();
                        Intent f20 = new Intent(MainActivity.this, MainActivity.class);
                        f20.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f20);
                        MaterialToast.makeText(context, "Malay", R.drawable.malaysia, Toast.LENGTH_LONG).show();
                        break;
                    case 20:

                        finalpop.start();
                        setLanguage("hi","IN");
                        LocaleHelper.setLocale(MainActivity.this,indiaLanguageCode);
                        finish();
                        Intent f21 = new Intent(MainActivity.this, MainActivity.class);
                        f21.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f21);
                        MaterialToast.makeText(context, "হিন্দী", R.drawable.india, Toast.LENGTH_LONG).show();
                        break;
                    case 21:

                        finalpop.start();
                        setLanguage("ur","PK");
                        LocaleHelper.setLocale(MainActivity.this,pakistanLanguageCode);
                        finish();
                        Intent f22 = new Intent(MainActivity.this, MainActivity.class);
                        f22.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f22);
                        MaterialToast.makeText(context, "اورد", R.drawable.pakistan, Toast.LENGTH_LONG).show();
                        break;
                    case 22:

                        finalpop.start();
                        setLanguage("bn","BD");
                        LocaleHelper.setLocale(MainActivity.this,bngalideshLanguageCode);
                        finish();
                        Intent f23 = new Intent(MainActivity.this, MainActivity.class);
                        f23.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f23);
                        MaterialToast.makeText(context, "বাংলা", R.drawable.bangladesh, Toast.LENGTH_LONG).show();
                        break;
                    case 23:

                        finalpop.start();
                        setLanguage("sv","SE");
                        LocaleHelper.setLocale(MainActivity.this,swideshLanguageCode);
                        finish();
                        Intent f24 = new Intent(MainActivity.this, MainActivity.class);
                        f24.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f24);
                        MaterialToast.makeText(context, "svenska", R.drawable.sweden, Toast.LENGTH_LONG).show();
                        break;
                    case 24:

                        finalpop.start();
                        setLanguage("sq","AL");
                        LocaleHelper.setLocale(MainActivity.this,albaniLanguageCode);
                        finish();
                        Intent f25 = new Intent(MainActivity.this, MainActivity.class);
                        f25.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f25);
                        MaterialToast.makeText(context, "shqipe", R.drawable.albania, Toast.LENGTH_LONG).show();
                        break;
                    case 25:

                        finalpop.start();
                        setLanguage("az","AZ");
                        LocaleHelper.setLocale(MainActivity.this,azrabiganLanguageCode);
                        finish();
                        Intent f26 = new Intent(MainActivity.this, MainActivity.class);
                        f26.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f26);
                        MaterialToast.makeText(context, "Azərbaycan dili", R.drawable.azerbaijan, Toast.LENGTH_LONG).show();
                        break;
                    case 26:

                        finalpop.start();
                        setLanguage("bs","BA");
                        LocaleHelper.setLocale(MainActivity.this,bosnaLanguageCode);
                        finish();
                        Intent f27 = new Intent(MainActivity.this, MainActivity.class);
                        f27.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f27);
                        MaterialToast.makeText(context, "Bosanski jezik", R.drawable.bosnia_and_herzegovina, Toast.LENGTH_LONG).show();
                        break;
                    case 27:

                        finalpop.start();
                        setLanguage("bg","BG");
                        LocaleHelper.setLocale(MainActivity.this,bulgaryLanguageCode);
                        finish();
                        Intent f28 = new Intent(MainActivity.this, MainActivity.class);
                        f28.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f28);
                        MaterialToast.makeText(context, "български", R.drawable.bulgaria, Toast.LENGTH_LONG).show();
                        break;
                    case 28:

                        finalpop.start();
                        setLanguage("ha","NG");
                        LocaleHelper.setLocale(MainActivity.this,nigeraLanguageCode);
                        finish();
                        Intent f29 = new Intent(MainActivity.this, MainActivity.class);
                        f29.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f29);
                        MaterialToast.makeText(context, "Hausa", R.drawable.nigeria, Toast.LENGTH_LONG).show();
                        break;
                    case 29:

                        finalpop.start();
                        setLanguage("no","NO");
                        LocaleHelper.setLocale(MainActivity.this,norwegLanguageCode);
                        finish();
                        Intent f30 = new Intent(MainActivity.this, MainActivity.class);
                        f30.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f30);
                        MaterialToast.makeText(context, "Norsk språk", R.drawable.norway, Toast.LENGTH_LONG).show();
                        break;
                    case 30:

                        finalpop.start();
                        setLanguage("so","SO");
                        LocaleHelper.setLocale(MainActivity.this,somailyLanguageCode);
                        finish();
                        Intent f31 = new Intent(MainActivity.this, MainActivity.class);
                        f31.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f31);
                        MaterialToast.makeText(context, "Afka Soomaaliga", R.drawable.somalia, Toast.LENGTH_LONG).show();
                        break;
                    case 31:

                        finalpop.start();
                        setLanguage("sw","KE");
                        LocaleHelper.setLocale(MainActivity.this,kenyaLanguageCode);
                        finish();
                        Intent f32 = new Intent(MainActivity.this, MainActivity.class);
                        f32.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(f32);
                        MaterialToast.makeText(context, "Lugha ya swahili", R.drawable.kenya, Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onMenuItemLongClick(View view, int itemPosition) {

            }
        });

        cycleMenuWidget.setStateChangeListener(new OnStateChangedListener() {
            @Override
            public void onStateChanged(CycleMenuWidget.STATE state) {

            }

            @Override
            public void onOpenComplete() {
                finalpop.start();
                FloatingToast.makeToast(MainActivity.this, getResources().getString(R.string.turnlang), FloatingToast.LENGTH_TOO_LONG)
                        .setGravity(FloatingToast.GRAVITY_BOTTOM)
                        .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                        .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                        .setBackgroundBlur(true)
                        .setFloatDistance(FloatingToast.DISTANCE_LONG)
                        .setTextColor(Color.parseColor("#ffffff"))
                        .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                        .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                        .show();    //Show toast at the specified fixed position
            }

            @Override
            public void onCloseComplete() {
                finalpop.start();
            }
        });


        cycleMenuWidget2 = findViewById(R.id.itemCycleMenuWidget2);
        cycleMenuWidget2.setMenuRes(R.menu.menu_net);
        cycleMenuWidget2.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View view, int itemPosition) {
                switch (itemPosition) {
                    case 0:
                        finalpop.start();
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.site_quran))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#134785"))
                                .show();
                            new FinestWebView.Builder(context).theme(R.style.FinestWebViewTheme).titleDefault(getResources().getString(R.string.quran_karem))
                                    .showUrl(false).webViewBuiltInZoomControls(true).webViewDisplayZoomControls(true)
                                    .statusBarColorRes(R.color.blueDark).toolbarColorRes(R.color.blue)
                                    .titleColorRes(R.color.finestWhite).urlColorRes(R.color.settings_title_bg)
                                    .iconDefaultColorRes(R.color.finestWhite).progressBarColorRes(R.color.finestWhite)
                                    .stringResCopiedToClipboard(R.string.copied_to_clipboard).swipeRefreshColorRes(R.color.blueDark)
                                    .menuSelector(R.drawable.selector_light_theme)
                                    .menuTextGravity(Gravity.CENTER_VERTICAL | Gravity.END)
                                    .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft).dividerHeight(0).gradientDivider(false)
                                    .setCustomAnimations(R.anim.slide_left_in, R.anim.hold, R.anim.hold, R.anim.slide_right_out)
                                    .disableIconBack(false).disableIconClose(false).disableIconForward(false).disableIconMenu(false)
                            .show(EasySharedPreference.Companion.getString(KEY_WEB, "https://quranenc.com/ar/browse/arabic_mokhtasar"));

                        break;
                    case 1:
                        finalpop.start();
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.qna_maka))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#CF8E08"))
                                .show();
                        startActivity(new Intent(context, YouMakaTube.class));
                        Bungee.maka(context);
                        break;
                    case 2:
                        finalpop.start();
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.qna_suna))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#296D60"))
                                .show();
                        startActivity(new Intent(context, YouSounaTube.class));
                        Bungee.souna(context);
                        break;
                }
            }

            @Override
            public void onMenuItemLongClick(View view, int itemPosition) {

            }
        });

        cycleMenuWidget2.setStateChangeListener(new OnStateChangedListener() {
            @Override
            public void onStateChanged(CycleMenuWidget.STATE state) {

            }

            @Override
            public void onOpenComplete() {
                finalpop.start();
                FloatingToast.makeToast(MainActivity.this, getResources().getString(R.string.internet_satalite), FloatingToast.LENGTH_TOO_LONG)
                        .setGravity(FloatingToast.GRAVITY_BOTTOM)
                        .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                        .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                        .setBackgroundBlur(true)
                        .setFloatDistance(FloatingToast.DISTANCE_LONG)
                        .setTextColor(Color.parseColor("#ffffff"))
                        .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                        .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                        .show();    //Show toast at the specified fixed position
            }

            @Override
            public void onCloseComplete() {
                finalpop.start();
            }
        });

    }


    private void logUser() {
             // TODO: Use the current user's information
             // You can call any combination of these three methods
        crashlytics = FirebaseCrashlytics.getInstance();
        crashlytics.setCrashlyticsCollectionEnabled(true);
        crashlytics.checkForUnsentReports();
        crashlytics.sendUnsentReports();
        crashlytics.setUserId("id-99999");
        crashlytics.setCustomKey("Name", "Sapeh");
        crashlytics.setCustomKey("Email", "asmmya7@gmail.com");


         }



    private void checkAppUpdate() {
        final int versionCode = BuildConfig.VERSION_CODE;

        final HashMap<String, Object> defaultMap = new HashMap<>();
        defaultMap.put(FB_RC_KEY_TITLE, getResources().getString(R.string.dialog_title));
        defaultMap.put(FB_RC_KEY_DESCRIPTION, getResources().getString(R.string.dialog_message));
        defaultMap.put(FB_RC_KEY_FORCE_UPDATE_VERSION, ""+versionCode);
        defaultMap.put(FB_RC_KEY_LATEST_VERSION, ""+versionCode);

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        mFirebaseRemoteConfig.setConfigSettingsAsync(new FirebaseRemoteConfigSettings.Builder().build());

        mFirebaseRemoteConfig.setDefaultsAsync(defaultMap);

        Task<Void> fetchTask=mFirebaseRemoteConfig.fetch(BuildConfig.DEBUG?0: TimeUnit.HOURS.toSeconds(4));

        fetchTask.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // After config data is successfully fetched, it must be activated before newly fetched
                    // values are returned.
                    new MaterialToast(context)
                        .setMessage(getResources().getString(R.string.fetchs))
                        .setIcon(R.drawable.wifi)
                        .setDuration(Toast.LENGTH_LONG)
                        .setBackgroundColor(Color.parseColor("#55b859"))
                        .show();
                    mFirebaseRemoteConfig.fetchAndActivate();

                    String title=getValue(FB_RC_KEY_TITLE,defaultMap);
                    String description=getValue(FB_RC_KEY_DESCRIPTION,defaultMap);
                    int forceUpdateVersion= Integer.parseInt(getValue(FB_RC_KEY_FORCE_UPDATE_VERSION,defaultMap));
                    int latestAppVersion= Integer.parseInt(getValue(FB_RC_KEY_LATEST_VERSION,defaultMap));

                    boolean isCancelable=true;

                    if(latestAppVersion>versionCode)
                    {
                        if(forceUpdateVersion>versionCode)
                            isCancelable=false;

                        appUpdateDialog = new AppUpdateDialog(MainActivity.this, title, description, isCancelable);
                        appUpdateDialog.setCancelable(false);
                        appUpdateDialog.show();

                        Window window = appUpdateDialog.getWindow();
                        assert window != null;
                        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                    }

                } else {

                    new MaterialToast(context)
                        .setMessage(getResources().getString(R.string.fetchs))
                        .setIcon(R.drawable.nowifi)
                        .setDuration(Toast.LENGTH_LONG)
                        .setBackgroundColor(Color.parseColor("#EE4035"))
                        .show();
                }
            }
        });
    }
    public String getValue(String parameterKey,HashMap<String, Object> defaultMap)
    {
        String value=mFirebaseRemoteConfig.getString(parameterKey);
        if(TextUtils.isEmpty(value))
            value= (String) defaultMap.get(parameterKey);

        return value;
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            backb.start();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        FloatingToast.makeToast(MainActivity.this, getResources().getString(R.string.back), FloatingToast.LENGTH_TOO_LONG)
            .setGravity(FloatingToast.GRAVITY_BOTTOM)
            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
            .setBackgroundBlur(true)
            .setFloatDistance(FloatingToast.DISTANCE_LONG)
            .setTextColor(Color.parseColor("#ffffff"))
            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
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

    public void onClick(View view) {

        mCounter = mCounter >= sentences.length - 1 ? 0 : mCounter + 1;
        hTextView.animateText(sentences[mCounter]);
    }



}

