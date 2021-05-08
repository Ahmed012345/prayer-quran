package com.zaker.android.sapeh.app.main.activitymain;


import android.Manifest;
import android.annotation.SuppressLint;

import android.content.*;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.marcoscg.materialtoast.MaterialToast;
import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.realpacific.clickshrinkeffect.ClickShrinkEffect;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.zaker.android.sapeh.*;
import com.zaker.android.sapeh.app.main.Bungee;
import com.zaker.android.sapeh.app.main.MainContract;
import com.zaker.android.sapeh.app.main.MainRecActivity;
import com.zaker.android.sapeh.app.main.activitylist.*;
import com.zaker.android.sapeh.app.main.guillotine.animation.GuillotineAnimation;
import com.zaker.android.sapeh.app.main.guillotine.interfaces.GuillotineListener;
import com.zaker.android.sapeh.app.main.bubble.BubbleLayout;

import java.util.*;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zaker.android.sapeh.app.main.foltingtoast.FloatingToast;
import com.zaker.android.sapeh.app.main.phoenix.PullToRefreshView;
import com.zaker.android.sapeh.app.main.cookiebar.CookieBar;
import com.zaker.android.sapeh.app.main.recordview.*;
import com.zaker.android.sapeh.util.AndroidUtils;

public class Main22Activity extends LocalizationActivity {

    public static final int REQ_CODE_REC_AUDIO_AND_WRITE_EXTERNAL = 101;
    public static final int REQ_CODE_RECORD_AUDIO = 303;
    public static final int REQ_CODE_WRITE_EXTERNAL_STORAGE = 404;
    public static final int REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT = 405;
    public static final int REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK = 406;
    public static final int REQ_CODE_IMPORT_AUDIO = 11;
    private MainContract.UserActionsListener presenter;

    private static final long RIPPLE_DURATION = 250;
    public static final int REFRESH_DELAY = 3000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.root22)
    FrameLayout root;
    @BindView(R.id.content_hamburger2)
    View contentHamburger;
    @BindView(R.id.list_view)
    ListView listView;

    GuillotineAnimation mGuillotineAnimation;
    boolean isOpened = true;
    PullToRefreshView mPullToRefreshView;
    Context context = this;
    MediaPlayer m1, azrard, backb, openk, closek, finalpop;
    boolean doubleBackToExitPressedOnce = false;
    ShineButton po_image, po_image1, po_image2;
    BubbleLayout tow2, tow, badge;
    AnimatedGradientTextView menu22;
    RecordView recordView;
    RecordButton recordButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        ButterKnife.bind(this);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        presenter = ARApplication.getInjector().provideMainPresenter();
        Map<String, Integer> map;
        List<Map<String, Integer>> sampleList = new ArrayList<>();

        final int[] colors = {R.color.o1, R.color.o2, R.color.o3, R.color.o4, R.color.o5,
                R.color.o6, R.color.o7, R.color.o8, R.color.o9, R.color.o10, R.color.o11,
                R.color.o12, R.color.o13, R.color.o14, R.color.o15, R.color.o16, R.color.o17,
                R.color.o18, R.color.o19, R.color.o20, R.color.o21, R.color.o22, R.color.o23,
                R.color.o24, R.color.o25, R.color.o26, R.color.o27, R.color.o28, R.color.o29,
                R.color.o30, R.color.o31, R.color.o32, R.color.o33, R.drawable.o_list};

        int[] BigNames = {R.string.dua_adm, R.string.duoa_yosof, R.string.doua_ayop,
                R.string.dua_noh, R.string.doua_shoaup, R.string.duaaebrahem,
                R.string.doua_ebrahemwesmail, R.string.doua_mosa, R.string.douaa_souliman,
                R.string.douaa_uonos, R.string.douaa_eisa, R.string.douaa_lot,
                R.string.douaa_zakria, R.string.doua_emratemran, R.string.doua_almoumen,
                R.string.douaa_altabin, R.string.douaa_mlaeka, R.string.doua_banyesrail,
                R.string.doua_m2monen, R.string.doua_shart, R.string.douaa_ansaresa,
                R.string.doua_atbaa, R.string.douaa_alftya, R.string.douaa_asia,
                R.string.douaa_ashap, R.string.douaa_mostdafin, R.string.doua_m2monenn,
                R.string.afdal_alduoaa, R.string.aya_alkorsi, R.string.eklass, R.string.flk,
                R.string.nas, R.string.duoa_nabina, R.string.surah21};

        int[] SmallNames = {R.string.auto, R.string.auto, R.string.auto, R.string.auto,
                R.string.auto, R.string.auto, R.string.autoo, R.string.auto, R.string.auto,
                R.string.auto, R.string.auto, R.string.auto, R.string.auto, R.string.autooo,
                R.string.autoooo, R.string.autooooo, R.string.salam, R.string.auto,
                R.string.salamm, R.string.salammm, R.string.auto, R.string.atba,
                R.string.atbaa, R.string.autooo, R.string.atbaaa, R.string.ard, R.string.ardd,
                R.string.alfateha, R.string.quran, R.string.quran, R.string.quran,
                R.string.quran, R.string.auto, R.string.quran};

        int[] icons = {R.drawable.ic_o1, R.drawable.ic_o2, R.drawable.ic_o3, R.drawable.ic_o4,
                R.drawable.ic_o5, R.drawable.ic_o6, R.drawable.ic_o7, R.drawable.ic_o8, R.drawable.ic_o9,
                R.drawable.ic_o10, R.drawable.ic_o11, R.drawable.ic_o12, R.drawable.ic_o13, R.drawable.ic_o14,
                R.drawable.ic_o15, R.drawable.ic_o16, R.drawable.ic_o17, R.drawable.ic_o18, R.drawable.ic_o19,
                R.drawable.ic_o20, R.drawable.ic_o21, R.drawable.ic_o22, R.drawable.ic_o23, R.drawable.ic_o24,
                R.drawable.ic_o25, R.drawable.ic_o26, R.drawable.ic_o27, R.drawable.ic_o28, R.drawable.ic_o29,
                R.drawable.ic_o30, R.drawable.ic_o31, R.drawable.ic_o32, R.drawable.ic_o33, R.drawable.ic_o34};


        for (int i = 0; i < BigNames.length; i++) {
            map = new HashMap<>();
            map.put(SampleAdapter.KEEY_NAME, BigNames[i]);
            map.put(SampleAdapter.KEY_NAME, SmallNames[i]);
            map.put(SampleAdapter.KEY_COLOR, colors[i]);
            map.put(SampleAdapter.KEY_ICON, icons[i]);
            sampleList.add(map);
        }

        listView = findViewById(R.id.list_view);
        listView.setAdapter(new SampleAdapter(this, R.layout.list_item, sampleList));
        mPullToRefreshView = findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.
                OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }
        @SuppressLint("InflateParams") final View guillotineMenu =
                LayoutInflater.from(this).inflate(R.layout.guillotine22, null);
        root.addView(guillotineMenu);

        tow = findViewById(R.id.tow);
        tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuillotineAnimation.close();
                FloatingToast.makeToast(Main22Activity.this, getResources().getString(R.string.dawat_alanbyaa), FloatingToast.LENGTH_TOO_LONG)
                        .setGravity(FloatingToast.GRAVITY_BOTTOM)
                        .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                        .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                        .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                        .setBackgroundBlur(true)
                        .setFloatDistance(FloatingToast.DISTANCE_LONG)
                        .setTextColor(Color.parseColor("#E56353"))
                        .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                        .show();    //Show toast at the specified fixed position
            }
        });
        tow2 = findViewById(R.id.tow2);
        tow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuillotineAnimation.close();
                FloatingToast.makeToast(Main22Activity.this, getResources().getString(R.string.dawat_alanbyaa), FloatingToast.LENGTH_TOO_LONG)
                        .setGravity(FloatingToast.GRAVITY_BOTTOM)
                        .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                        .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                        .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                        .setBackgroundBlur(true)
                        .setFloatDistance(FloatingToast.DISTANCE_LONG)
                        .setTextColor(Color.parseColor("#E56353"))
                        .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                        .show();    //Show toast at the specified fixed position
            }
        });

        openk = MediaPlayer.create(this, R.raw.openk);
        closek = MediaPlayer.create(this, R.raw.closek);
        mGuillotineAnimation = new GuillotineAnimation.GuillotineBuilder(guillotineMenu,
                guillotineMenu.findViewById(R.id.guillotine_hamburger3),
                contentHamburger)
                .setActionBarViewForAnimation(toolbar)
                .setStartDelay(RIPPLE_DURATION)
                .setClosedOnStart(true)
                .setGuillotineListener(new GuillotineListener() {
                    @Override
                    public void onGuillotineOpened() {
                        isOpened = true;
                        openk.start();
                        po_image2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                po_image2.setChecked(false);
                                if (m1.isPlaying()) {
                                    m1.stop();
                                    m1.prepareAsync();
                                    m1.seekTo(0);
                                    FloatingToast.makeToast(Main22Activity.this, getResources().getString(R.string.stop), FloatingToast.LENGTH_TOO_LONG)
                                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                                            .setBackgroundBlur(true)
                                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                                            .setTextColor(Color.parseColor("#008b8b"))
                                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                                            .show();    //Show toast at the specified fixed position
                                } else {
                                    po_image2.setChecked(true);
                                    m1.start();
                                    FloatingToast.makeToast(Main22Activity.this, getResources().getString(R.string.play), FloatingToast.LENGTH_TOO_LONG)
                                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                                            .setBackgroundBlur(true)
                                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                                            .setTextColor(Color.parseColor("#ffffff"))
                                            .setShadowLayer(5, 1, 1, Color.parseColor("#008b8b"))
                                            .show();    //Show toast at the specified fixed position

                                }
                            }
                        });
                        po_image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (po_image.isChecked()) {
                                    finalpop.start();
                                    FloatingToast.makeToast(Main22Activity.this, getResources().getString(R.string.sourah2), FloatingToast.LENGTH_TOO_LONG)
                                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                                            .setBackgroundBlur(true)
                                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                                            .setTextColor(Color.parseColor("#008b8b"))
                                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                                            .show();    //Show toast at the specified fixed position
                                }
                            }
                        });
                        po_image1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (po_image1.isChecked()) {
                                    finalpop.start();
                                    FloatingToast.makeToast(Main22Activity.this, getResources().getString(R.string.z186), FloatingToast.LENGTH_TOO_LONG)
                                            .setGravity(FloatingToast.GRAVITY_BOTTOM)
                                            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                                            .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._5mdp))
                                            .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                                            .setBackgroundBlur(true)
                                            .setFloatDistance(FloatingToast.DISTANCE_LONG)
                                            .setTextColor(Color.parseColor("#008b8b"))
                                            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                                            .show();    //Show toast at the specified fixed position
                                }
                            }
                        });
                    }

                    @Override
                    public void onGuillotineClosed() {
                        isOpened = false;
                        closek.start();
                        po_image.setChecked(false);
                        po_image1.setChecked(false);
                        po_image2.setChecked(false);
                        if (m1.isPlaying()) {
                            m1.stop();
                            m1.prepareAsync();
                            m1.seekTo(0);
                        }

                    }
                })
                .build();


        m1 = MediaPlayer.create(this, R.raw.ba12ra186);


        azrard = MediaPlayer.create(this, R.raw.azrard);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        azrard.start();
                        startActivity(new Intent(context, Activity1List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list1))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#D77186"))
                                .show();
                        break;
                    case 1:
                        azrard.start();
                        startActivity(new Intent(context, Activity2List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list2))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#61A2DA"))
                                .show();
                        break;
                    case 2:
                        azrard.start();
                        startActivity(new Intent(context, Activity3List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list3))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#D75725"))
                                .show();
                        break;
                    case 3:
                        azrard.start();
                        startActivity(new Intent(context, Activity4List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list4))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#008b8b"))
                                .show();
                        break;
                    case 4:
                        azrard.start();
                        startActivity(new Intent(context, Activity5List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list5))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#556b2f"))
                                .show();
                        break;
                    case 5:
                        azrard.start();
                        startActivity(new Intent(context, Activity6List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list6))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#a52a2a"))
                                .show();
                        break;
                    case 6:
                        azrard.start();
                        startActivity(new Intent(context, Activity7List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list7))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#2E7CA8"))
                                .show();
                        break;
                    case 7:
                        azrard.start();
                        startActivity(new Intent(context, Activity8List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list8))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#C11432"))
                                .show();
                        break;
                    case 8:
                        azrard.start();
                        startActivity(new Intent(context, Activity9List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list9))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#7A989A"))
                                .show();
                        break;
                    case 9:
                        azrard.start();
                        startActivity(new Intent(context, Activity10List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list10))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#9932cc"))
                                .show();
                        break;
                    case 10:
                        azrard.start();
                        startActivity(new Intent(context, Activity11List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list11))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#80B9CE"))
                                .show();
                        break;
                    case 11:
                        azrard.start();
                        startActivity(new Intent(context, Activity12List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list12))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#d2691e"))
                                .show();
                        break;
                    case 12:
                        azrard.start();
                        startActivity(new Intent(context, Activity13List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list13))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#62496F"))
                                .show();
                        break;
                    case 13:
                        azrard.start();
                        startActivity(new Intent(context, Activity14List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list14))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#293757"))
                                .show();
                        break;
                    case 14:
                        azrard.start();
                        startActivity(new Intent(context, Activity15List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.doua_almoumen))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#568D4B"))
                                .show();
                        break;
                    case 15:
                        azrard.start();
                        startActivity(new Intent(context, Activity16List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.douaa_altabin))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#6495ed"))
                                .show();
                        break;
                    case 16:
                        azrard.start();
                        startActivity(new Intent(context, Activity17List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list17))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#D26A1B"))
                                .show();
                        break;
                    case 17:
                        azrard.start();
                        startActivity(new Intent(context, Activity18List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list18))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#A41D1A"))
                                .show();
                        break;
                    case 18:
                        azrard.start();
                        startActivity(new Intent(context, Activity19List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.doua_m2monen))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#A4B7E1"))
                                .show();
                        break;
                    case 19:
                        azrard.start();
                        startActivity(new Intent(context, Activity20List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list20))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#B8B87A"))
                                .show();
                        break;
                    case 20:
                        azrard.start();
                        startActivity(new Intent(context, Activity21List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list21))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#EFBD37"))
                                .show();
                        break;
                    case 21:
                        azrard.start();
                        startActivity(new Intent(context, Activity22List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.doua_atbaa))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#A85E5E"))
                                .show();
                        break;
                    case 22:
                        azrard.start();
                        startActivity(new Intent(context, Activity23List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list23))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#4682B4"))
                                .show();
                        break;
                    case 23:
                        azrard.start();
                        startActivity(new Intent(context, Activity24List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list24))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#90A74A"))
                                .show();
                        break;
                    case 24:
                        azrard.start();
                        startActivity(new Intent(context, Activity25List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.douaa_ashap))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#C1395E"))
                                .show();
                        break;
                    case 25:
                        azrard.start();
                        startActivity(new Intent(context, Activity26List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list26))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#3F6148"))
                                .show();
                        break;
                    case 26:
                        azrard.start();
                        startActivity(new Intent(context, Activity27List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.doua_m2monenn))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#A4804C"))
                                .show();
                        break;
                    case 27:
                        azrard.start();
                        startActivity(new Intent(context, Activity28List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list28))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#dc143c"))
                                .show();
                        break;
                    case 28:
                        azrard.start();
                        startActivity(new Intent(context, Activity29List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.aya_alkorsi))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#DA73A8"))
                                .show();
                        break;
                    case 29:
                        azrard.start();
                        startActivity(new Intent(context, Activity30List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.eklass))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#4052BD"))
                                .show();
                        break;
                    case 30:
                        azrard.start();
                        startActivity(new Intent(context, Activity31List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.flk))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#197C3F"))
                                .show();
                        break;
                    case 31:
                        azrard.start();
                        startActivity(new Intent(context, Activity32List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.nas))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#8E44AD"))
                                .show();
                        break;
                    case 32:
                        azrard.start();
                        startActivity(new Intent(context, Activity33List.class));
                        Bungee.shrink(context);
                        new MaterialToast(context)
                                .setMessage(getResources().getString(R.string.list33))
                                .setIcon(R.mipmap.ic_kaba1)
                                .setDuration(Toast.LENGTH_LONG)
                                .setBackgroundColor(Color.parseColor("#C74C25"))
                                .show();
                        break;
                    case 33:
                        azrard.start();
                        startActivity(new Intent(context, Activity34List.class));
                        Bungee.shrink(context);
                        MaterialToast.makeText(Main22Activity.this, getResources().getString(R.string.surah21),
                                R.mipmap.ic_kaba1,
                                Toast.LENGTH_LONG).setBackgroundColor(Color.WHITE).show();
                        break;
                }
            }
        });


        backb = MediaPlayer.create(this, R.raw.backb);

        po_image = findViewById(R.id.po_image);
        po_image1 = findViewById(R.id.po_image1);
        po_image2 = findViewById(R.id.po_image2);

        finalpop = MediaPlayer.create(this, R.raw.finalpop);

        badge = findViewById(R.id.badge);
        menu22 = findViewById(R.id.menu22);

        new ClickShrinkEffect(menu22);
        new ClickShrinkEffect(badge);

        if (context.getString(R.string.lang).equals("ar")) {

            menu22.setTypeface(FontManger.yad);
        }
        if (context.getString(R.string.lang).equals("zh")) {

            menu22.setTypeface(FontManger.asian);
        }
        if (context.getString(R.string.lang).equals("cs")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("nl")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("en")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("fr")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("de")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("in")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("it")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("ja")) {

            menu22.setTypeface(FontManger.asian);
        }
        if (context.getString(R.string.lang).equals("ko")) {

            menu22.setTypeface(FontManger.asian);
        }
        if (context.getString(R.string.lang).equals("fa")) {

            menu22.setTypeface(FontManger.yad);
        }
        if (context.getString(R.string.lang).equals("pl")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("pt")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("ro")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("ru")) {

            menu22.setTypeface(FontManger.russi);
        }
        if (context.getString(R.string.lang).equals("es")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("th")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("tr")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("ms")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("hi")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("bn")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("sv")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("sq")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("az")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("bs")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("ur")) {

            menu22.setTypeface(FontManger.yad);
        }
        if (context.getString(R.string.lang).equals("bg")) {

            menu22.setTypeface(FontManger.russi);
        }
        if (context.getString(R.string.lang).equals("ha")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("no")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("so")) {

            menu22.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("sw")) {

            menu22.setTypeface(FontManger.english);
        }

        GradientDrawable shapeDrawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.recv_bg_mic);
        shapeDrawable.setColor(getResources().getColor(R.color.folo));
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

                CookieBar.build(Main22Activity.this)
                        .setTitle(getResources().getString(R.string.hold_button_to_record))
                        .setTitleColor(R.color.o1)
                        .setMessage(getResources().getString(R.string.hold_button_to_record2))
                        .setMessageColor(R.color.default_message_color)
                        .setIcon(R.drawable.recv_ic_mic_white)
                        .setBackgroundColor(R.color.holo)
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
                        .setBackgroundColor(getResources().getColor(R.color.folo))
                        .show();
                Log.d("RecordView", "onFinish");


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


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            backb.start();
            m1.stop();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        FloatingToast.makeToast(Main22Activity.this, getResources().getString(R.string.finalback), FloatingToast.LENGTH_TOO_LONG)
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
                doubleBackToExitPressedOnce = false;
                Bungee.shrink(context);

            }
        }, 2000);
    }

    @SuppressLint("DefaultLocale")
    private String getHumanTimeText(long milliseconds) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milliseconds),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
    }
    public void startFileSelector() {
        Intent intent_upload = new Intent();
        intent_upload.setType("audio/*");
        intent_upload.addCategory(Intent.CATEGORY_OPENABLE);
//		intent_upload.setAction(Intent.ACTION_GET_CONTENT);
        intent_upload.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(intent_upload, REQ_CODE_IMPORT_AUDIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_IMPORT_AUDIO && resultCode == RESULT_OK){
            presenter.importAudioFile(getApplicationContext(), data.getData());
        }
    }

    private boolean checkStoragePermissionImport() {
        if (presenter.isStorePublic()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                            new String[]{
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkStoragePermissionPlayback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK);
                return false;
            }
        }
        return true;
    }

    private boolean checkRecordPermission2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQ_CODE_RECORD_AUDIO);
                return false;
            }
        }
        return true;
    }

    private boolean checkStoragePermission2() {
        if (presenter.isStorePublic()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    AndroidUtils.showDialog(this, R.string.warning, R.string.need_write_permission,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    requestPermissions(
                                            new String[]{
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                    Manifest.permission.READ_EXTERNAL_STORAGE},
                                            REQ_CODE_WRITE_EXTERNAL_STORAGE);
                                }
                            }, null
//							new View.OnClickListener() {
//								@Override
//								public void onClick(View v) {
//									presenter.setStoragePrivate(getApplicationContext());
//									presenter.startRecording();
//								}
//							}
                    );
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQ_CODE_REC_AUDIO_AND_WRITE_EXTERNAL && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
            presenter.startRecording(getApplicationContext());
        } else if (requestCode == REQ_CODE_RECORD_AUDIO && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (checkStoragePermission2()) {
                presenter.startRecording(getApplicationContext());
            }
        } else if (requestCode == REQ_CODE_WRITE_EXTERNAL_STORAGE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            if (checkRecordPermission2()) {
                presenter.startRecording(getApplicationContext());
            }
        } else if (requestCode == REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            startFileSelector();
        } else if (requestCode == REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            presenter.startPlayback();
        } else if (requestCode == REQ_CODE_WRITE_EXTERNAL_STORAGE && grantResults.length > 0
                && (grantResults[0] == PackageManager.PERMISSION_DENIED
                || grantResults[1] == PackageManager.PERMISSION_DENIED)) {
            presenter.setStoragePrivate(getApplicationContext());
            presenter.startRecording(getApplicationContext());
        }
    }
}