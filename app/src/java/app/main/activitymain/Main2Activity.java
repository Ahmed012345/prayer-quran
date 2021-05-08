package com.zaker.android.sapeh.app.main.activitymain;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.marcoscg.materialtoast.MaterialToast;
import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.ramotion.foldingcell.FoldingCell;
import com.realpacific.clickshrinkeffect.ClickShrinkEffect;
import com.zaker.android.sapeh.ARApplication;
import com.zaker.android.sapeh.app.main.MainContract;
import com.zaker.android.sapeh.app.main.MainRecActivity;
import com.zaker.android.sapeh.app.main.cookiebar.CookieBar;
import com.zaker.android.sapeh.app.main.guillotine.animation.GuillotineAnimation;
import com.zaker.android.sapeh.app.main.guillotine.interfaces.GuillotineListener;
import com.zaker.android.sapeh.app.main.Bungee;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.main.bubble.BubbleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.zaker.android.sapeh.app.main.foltingtoast.FloatingToast;
import com.zaker.android.sapeh.app.main.recordview.*;
import com.zaker.android.sapeh.util.AndroidUtils;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main2Activity extends LocalizationActivity {

    public static final int REQ_CODE_REC_AUDIO_AND_WRITE_EXTERNAL = 101;
    public static final int REQ_CODE_RECORD_AUDIO = 303;
    public static final int REQ_CODE_WRITE_EXTERNAL_STORAGE = 404;
    public static final int REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT = 405;
    public static final int REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK = 406;
    public static final int REQ_CODE_IMPORT_AUDIO = 11;

    private MainContract.UserActionsListener presenter;
    RecordView recordView;
    RecordButton recordButton;

    private static final long RIPPLE_DURATION = 250;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.root)
    FrameLayout root;
    @BindView(R.id.content_hamburger2)
    View contentHamburger;

    MediaPlayer mMediaPlayer,backb,openk,closek,pages;
    Context context = this;
    GuillotineAnimation mGuillotineAnimation;
    boolean isOpened = true;
    boolean doubleBackToExitPressedOnce = false;

    BubbleLayout frist1,frst,badge,badge1,badge3,badge4,badge5,badge6;

    AnimatedGradientTextView menu1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        presenter = ARApplication.getInjector().provideMainPresenter();
        Map<String, Integer> map;
        List<Map<String, Integer>> cellList = new ArrayList<>();


        int[] lon_out = {R.color.e1,R.color.e2,R.color.e3,R.color.e4,R.color.e5,R.color.e6,R.color.e7,R.color.e8,R.color.e9,
                R.color.e10,R.color.e11,R.color.e12,R.color.e13,R.color.e14,R.color.e15,R.color.e16,R.color.e17,R.color.e18,
                R.color.e19,R.color.e20,R.color.e21,R.color.e22,R.color.e23,R.color.e24,R.color.e25,R.color.e26,R.color.e27,
                R.color.e28,R.color.e29,R.color.e30,R.color.e31,R.color.e32,R.color.e33,R.color.e34,R.color.e35,R.color.e36,
                R.color.e37,R.color.e38,R.color.e39,R.color.e40,R.color.e41,R.color.e42,R.color.e43,R.color.e44,R.color.e45,
                R.color.e46,R.color.e47,R.color.e48,R.color.e49,R.color.e50,R.color.e51,R.color.e52,R.color.e53,R.color.e54,
                R.color.e55,R.color.e56,R.color.e57,R.color.e58,R.color.e59,R.color.e60,R.color.e61,R.color.e62,R.color.e63,
                R.color.e64,R.color.e65,R.color.e66,R.color.e67,R.color.e68,R.color.e69,R.color.e70,R.color.e71,R.color.e72,
                R.color.e73,R.color.e74,R.color.e75,R.color.e76,R.color.e77,R.color.e78,R.color.e79,R.color.e80,R.color.e81,
                R.color.e82,R.color.e83,R.color.e84,R.color.e85,R.color.e86,R.color.e87,R.color.e88,R.color.e89,R.color.e90,
                R.color.e91,R.color.e92,R.color.e93,R.color.e94,R.color.e95,R.color.e96,R.color.e97,R.color.e98,R.color.e99};

        int[] lon_in = {R.color.e1,R.color.e2,R.color.e3,R.color.e4,R.color.e5,R.color.e6,R.color.e7,R.color.e8,R.color.e9,
                R.color.e10,R.color.e11,R.color.e12,R.color.e13,R.color.e14,R.color.e15,R.color.e16,R.color.e17,R.color.e18,
                R.color.e19,R.color.e20,R.color.e21,R.color.e22,R.color.e23,R.color.e24,R.color.e25,R.color.e26,R.color.e27,
                R.color.e28,R.color.e29,R.color.e30,R.color.e31,R.color.e32,R.color.e33,R.color.e34,R.color.e35,R.color.e36,
                R.color.e37,R.color.e38,R.color.e39,R.color.e40,R.color.e41,R.color.e42,R.color.e43,R.color.e44,R.color.e45,
                R.color.e46,R.color.e47,R.color.e48,R.color.e49,R.color.e50,R.color.e51,R.color.e52,R.color.e53,R.color.e54,
                R.color.e55,R.color.e56,R.color.e57,R.color.e58,R.color.e59,R.color.e60,R.color.e61,R.color.e62,R.color.e63,
                R.color.e64,R.color.e65,R.color.e66,R.color.e67,R.color.e68,R.color.e69,R.color.e70,R.color.e71,R.color.e72,
                R.color.e73,R.color.e74,R.color.e75,R.color.e76,R.color.e77,R.color.e78,R.color.e79,R.color.e80,R.color.e81,
                R.color.e82,R.color.e83,R.color.e84,R.color.e85,R.color.e86,R.color.e87,R.color.e88,R.color.e89,R.color.e90,
                R.color.e91,R.color.e92,R.color.e93,R.color.e94,R.color.e95,R.color.e96,R.color.e97,R.color.e98,R.color.e99};

        int[] numbers_out = {R.string.z1,R.string.z2,R.string.z3,R.string.z4,R.string.z5,R.string.z6,R.string.z7,R.string.z8,
                R.string.z9,R.string.z10,R.string.z11,R.string.z12,R.string.z13,R.string.z14,R.string.z15,R.string.z16,R.string.z17,
                R.string.z18,R.string.z19,R.string.z20,R.string.z21,R.string.z22,R.string.z23,R.string.z24,R.string.z25,
                R.string.z26,R.string.z27,R.string.z28,R.string.z29,R.string.z30,R.string.z31,R.string.z31,R.string.z33,
                R.string.z34, R.string.z35,R.string.z36,R.string.z37,R.string.z38,R.string.z39,R.string.z40,R.string.z41,
                R.string.z42,R.string.z43,R.string.z44,R.string.z45,R.string.z46,R.string.z47,R.string.z48,R.string.z49,
                R.string.z50,R.string.z51,R.string.z52,R.string.z53,R.string.z54,R.string.z55,R.string.z56,R.string.z57,
                R.string.z58,R.string.z59,R.string.z60,R.string.z61,R.string.z62,R.string.z63,R.string.z64,R.string.z65,
                R.string.z66,R.string.z67,R.string.z68,R.string.z69,R.string.z70,R.string.z71,R.string.z72,R.string.z73,
                R.string.z74,R.string.z75,R.string.z76,R.string.z77, R.string.z78,R.string.z79,R.string.z80,R.string.z81,
                R.string.z82,R.string.z83,R.string.z84,R.string.z85,R.string.z86,R.string.z87,R.string.z88,R.string.z89,
                R.string.z90,R.string.z91,R.string.z92,R.string.z93,R.string.z94,R.string.z95,R.string.z96,R.string.z97,
                R.string.z98,R.string.z99};

        int[] numbers_in = {R.string.z1,R.string.z2,R.string.z3,R.string.z4,R.string.z5,R.string.z6,R.string.z7,R.string.z8,
                R.string.z9,R.string.z10,R.string.z11,R.string.z12,R.string.z13,R.string.z14,R.string.z15,R.string.z16,R.string.z17,
                R.string.z18,R.string.z19,R.string.z20,R.string.z21,R.string.z22,R.string.z23,R.string.z24,R.string.z25,
                R.string.z26,R.string.z27,R.string.z28,R.string.z29,R.string.z30,R.string.z31,R.string.z31,R.string.z33,
                R.string.z34, R.string.z35,R.string.z36,R.string.z37,R.string.z38,R.string.z39,R.string.z40,R.string.z41,
                R.string.z42,R.string.z43,R.string.z44,R.string.z45,R.string.z46,R.string.z47,R.string.z48,R.string.z49,
                R.string.z50,R.string.z51,R.string.z52,R.string.z53,R.string.z54,R.string.z55,R.string.z56,R.string.z57,
                R.string.z58,R.string.z59,R.string.z60,R.string.z61,R.string.z62,R.string.z63,R.string.z64,R.string.z65,
                R.string.z66,R.string.z67,R.string.z68,R.string.z69,R.string.z70,R.string.z71,R.string.z72,R.string.z73,
                R.string.z74,R.string.z75,R.string.z76,R.string.z77, R.string.z78,R.string.z79,R.string.z80,R.string.z81,
                R.string.z82,R.string.z83,R.string.z84,R.string.z85,R.string.z86,R.string.z87,R.string.z88,R.string.z89,
                R.string.z90,R.string.z91,R.string.z92,R.string.z93,R.string.z94,R.string.z95,R.string.z96,R.string.z97,
                R.string.z98,R.string.z99};


        int[] image_in = {R.drawable.ee1,R.drawable.ee2,R.drawable.ee3,R.drawable.ee4,R.drawable.ee5,
                R.drawable.ee6,R.drawable.ee7,R.drawable.ee8,R.drawable.ee9,R.drawable.ee10,R.drawable.ee11,
                R.drawable.ee12,R.drawable.ee13,R.drawable.ee14,R.drawable.ee15,R.drawable.ee16,R.drawable.ee17,
                R.drawable.ee18,R.drawable.ee19,R.drawable.ee20,R.drawable.ee21,R.drawable.ee22,R.drawable.ee23,
                R.drawable.ee24,R.drawable.ee25,R.drawable.ee26,R.drawable.ee27,R.drawable.ee28,R.drawable.ee29,
                R.drawable.ee30,R.drawable.ee31,R.drawable.ee32,R.drawable.ee33,R.drawable.ee34,R.drawable.ee35,
                R.drawable.ee36,R.drawable.ee37,R.drawable.ee38,R.drawable.ee39,R.drawable.ee40,R.drawable.ee41,
                R.drawable.ee42,R.drawable.ee43,R.drawable.ee44,R.drawable.ee45,R.drawable.ee46,R.drawable.ee47,
                R.drawable.ee48,R.drawable.ee49,R.drawable.ee50,R.drawable.ee51,R.drawable.ee52,R.drawable.ee53,
                R.drawable.ee54,R.drawable.ee55,R.drawable.ee56,R.drawable.ee57,R.drawable.ee58,R.drawable.ee59,
                R.drawable.ee60,R.drawable.ee61,R.drawable.ee62,R.drawable.ee63,R.drawable.ee64,R.drawable.ee65,
                R.drawable.ee66,R.drawable.ee67,R.drawable.ee68,R.drawable.ee69,R.drawable.ee70,R.drawable.ee71,
                R.drawable.ee72,R.drawable.ee73,R.drawable.ee74,R.drawable.ee75,R.drawable.ee76,R.drawable.ee77,
                R.drawable.ee78,R.drawable.ee79,R.drawable.ee80,R.drawable.ee81,R.drawable.ee82,R.drawable.ee83,
                R.drawable.ee84,R.drawable.ee85,R.drawable.ee86,R.drawable.ee87,R.drawable.ee88,R.drawable.ee89,
                R.drawable.ee90,R.drawable.ee91,R.drawable.ee92,R.drawable.ee93,R.drawable.ee94,R.drawable.ee95,
                R.drawable.ee96,R.drawable.ee97,R.drawable.ee98,R.drawable.ee99};




        int[] text1out = {R.string.esm0,R.string.esm1,R.string.esm2,R.string.esm3,R.string.esm4,R.string.esm5,R.string.esm6,
                R.string.esm7,R.string.esm8,R.string.esm9,R.string.esm10,R.string.esm11,R.string.esm12,R.string.esm13,
                R.string.esm14,R.string.esm15,R.string.esm16,R.string.esm17,R.string.esm18,R.string.esm19,R.string.esm20,
                R.string.esm21,R.string.esm22,R.string.esm23,R.string.esm24,R.string.esm25,R.string.esm26,R.string.esm27,
                R.string.esm28,R.string.esm29,R.string.esm30,R.string.esm31,R.string.esm32, R.string.esm33, R.string.esm34,
                R.string.esm35,R.string.esm36,R.string.esm37,R.string.esm38,R.string.esm39,R.string.esm40,R.string.esm41,
                R.string.esm42,R.string.esm43,R.string.esm44,R.string.esm45,R.string.esm46,R.string.esm47,R.string.esm48,
                R.string.esm49,R.string.esm50,R.string.esm51,R.string.esm52,R.string.esm53,R.string.esm54,R.string.esm55,
                R.string.esm56,R.string.esm57,R.string.esm58,R.string.esm59,R.string.esm60,R.string.esm61,R.string.esm62,
                R.string.esm63,R.string.esm64,R.string.esm65,R.string.esm66,R.string.esm68,R.string.esm69,R.string.esm70,
                R.string.esm71,R.string.esm72,R.string.esm73,R.string.esm74,R.string.esm75,R.string.esm76, R.string.esm77,
                R.string.esm78,R.string.esm79,R.string.esm80,R.string.esm81,R.string.esm82,R.string.esm83,R.string.esm84,
                R.string.esm85,R.string.esm86,R.string.esm87,R.string.esm88,R.string.esm89,R.string.esm90,R.string.esm91,
                R.string.esm92,R.string.esm93,R.string.esm94,R.string.esm95,R.string.esm96,R.string.esm97,R.string.esm98,
                R.string.esm99};


        int[] text1in = {R.string.esm0,R.string.esm1,R.string.esm2,R.string.esm3,R.string.esm4,R.string.esm5,R.string.esm6,
                R.string.esm7,R.string.esm8,R.string.esm9,R.string.esm10,R.string.esm11,R.string.esm12,R.string.esm13,
                R.string.esm14,R.string.esm15,R.string.esm16,R.string.esm17,R.string.esm18,R.string.esm19,R.string.esm20,
                R.string.esm21,R.string.esm22,R.string.esm23,R.string.esm24,R.string.esm25,R.string.esm26,R.string.esm27,
                R.string.esm28,R.string.esm29,R.string.esm30,R.string.esm31,R.string.esm32, R.string.esm33, R.string.esm34,
                R.string.esm35,R.string.esm36,R.string.esm37,R.string.esm38,R.string.esm39,R.string.esm40,R.string.esm41,
                R.string.esm42,R.string.esm43,R.string.esm44,R.string.esm45,R.string.esm46,R.string.esm47,R.string.esm48,
                R.string.esm49,R.string.esm50,R.string.esm51,R.string.esm52,R.string.esm53,R.string.esm54,R.string.esm55,
                R.string.esm56,R.string.esm57,R.string.esm58,R.string.esm59,R.string.esm60,R.string.esm61,R.string.esm62,
                R.string.esm63,R.string.esm64,R.string.esm65,R.string.esm66,R.string.esm68,R.string.esm69,R.string.esm70,
                R.string.esm71,R.string.esm72,R.string.esm73,R.string.esm74,R.string.esm75,R.string.esm76, R.string.esm77,
                R.string.esm78,R.string.esm79,R.string.esm80,R.string.esm81,R.string.esm82,R.string.esm83,R.string.esm84,
                R.string.esm85,R.string.esm86,R.string.esm87,R.string.esm88,R.string.esm89,R.string.esm90,R.string.esm91,
                R.string.esm92,R.string.esm93,R.string.esm94,R.string.esm95,R.string.esm96,R.string.esm97,R.string.esm98,
                R.string.esm99};


        int[] text1content = {R.string.esm00,R.string.esm1_1,R.string.esm2_2,R.string.esm3_3,R.string.esm4_4,
                R.string.esm5_5,R.string.esm6_6,R.string.esm7_7,R.string.esm8_8,R.string.esm9_9,R.string.esm1010,
                R.string.esm1111,R.string.esm1212,R.string.esm1313,R.string.esm1414,R.string.esm1515,R.string.esm1616,
                R.string.esm1717,R.string.esm1818,R.string.esm1919,R.string.esm2020,R.string.esm2121,R.string.esm2222,
                R.string.esm2323,R.string.esm2424,R.string.esm2525,R.string.esm2626,R.string.esm2727,R.string.esm2828,
                R.string.esm2929,R.string.esm3030,R.string.esm3131,R.string.esm3232, R.string.esm3333, R.string.esm3434,
                R.string.esm3535,R.string.esm3636,R.string.esm3737,R.string.esm3838,R.string.esm3939,R.string.esm4040,
                R.string.esm4141,R.string.esm4242,R.string.esm4343,R.string.esm4444,R.string.esm4545,R.string.esm4646,
                R.string.esm4747,R.string.esm4848,R.string.esm4949,R.string.esm5050,R.string.esm5151,R.string.esm5252,
                R.string.esm5353,R.string.esm5454,R.string.esm5555,R.string.esm5656,R.string.esm5757,R.string.esm5858,
                R.string.esm5959,R.string.esm6060,R.string.esm6161,R.string.esm6262,R.string.esm6363,R.string.esm6464,
                R.string.esm6565,R.string.esm6666,R.string.esm6868,R.string.esm6969,R.string.esm7070,R.string.esm7171,
                R.string.esm7272,R.string.esm7373,R.string.esm7474,R.string.esm7575,R.string.esm7676, R.string.esm7777,
                R.string.esm7878,R.string.esm7979,R.string.esm8080,R.string.esm8181,R.string.esm8282,R.string.esm8383,
                R.string.esm8484,R.string.esm8585,R.string.esm8686,R.string.esm8787,R.string.esm8888,R.string.esm8989,
                R.string.esm9090,R.string.esm9191,R.string.esm9292,R.string.esm9393,R.string.esm9494,R.string.esm9595,
                R.string.esm9696,R.string.esm9797,R.string.esm9898,R.string.esm9999};

        int[] text2content = {R.string.esm0000,R.string.esm111,R.string.esm222,R.string.esm333,R.string.esm444,
                R.string.esm555,R.string.esm666,R.string.esm777,R.string.esm888,R.string.esm999,R.string.esm101010,
                R.string.esm111111,R.string.esm121212,R.string.esm131313,R.string.esm141414,R.string.esm151515,R.string.esm161616,
                R.string.esm171717,R.string.esm181818,R.string.esm191919,R.string.esm202020,R.string.esm212121,R.string.esm222222,
                R.string.esm232323,R.string.esm242424,R.string.esm252525,R.string.esm262626,R.string.esm272727,R.string.esm282828,
                R.string.esm292929,R.string.esm303030,R.string.esm313131,R.string.esm323232, R.string.esm333333, R.string.esm343434,
                R.string.esm353535,R.string.esm363636,R.string.esm373737,R.string.esm383838,R.string.esm393939,R.string.esm404040,
                R.string.esm414141,R.string.esm424242,R.string.esm434343,R.string.esm444444,R.string.esm454545,R.string.esm464646,
                R.string.esm474747,R.string.esm484848,R.string.esm494949,R.string.esm505050,R.string.esm515151,R.string.esm525252,
                R.string.esm535353,R.string.esm545454,R.string.esm555555,R.string.esm565656,R.string.esm575757,R.string.esm585858,
                R.string.esm595959,R.string.esm606060,R.string.esm616161,R.string.esm626262,R.string.esm636363,R.string.esm646464,
                R.string.esm656565,R.string.esm666666,R.string.esm686868,R.string.esm696969,R.string.esm707070,R.string.esm717171,
                R.string.esm727272,R.string.esm737373,R.string.esm747474,R.string.esm757575,R.string.esm767676, R.string.esm777777,
                R.string.esm787878,R.string.esm797979,R.string.esm808080,R.string.esm818181,R.string.esm828282,R.string.esm838383,
                R.string.esm848484,R.string.esm858585,R.string.esm868686,R.string.esm878787,R.string.esm888888,R.string.esm898989,
                R.string.esm909090,R.string.esm919191,R.string.esm929292,R.string.esm939393,R.string.esm949494,R.string.esm959595,
                R.string.esm969696,R.string.esm979797,R.string.esm989898,R.string.esm999999};

        int[] text3content = {R.string.esm00000,R.string.esm11_11,R.string.esm22_22,R.string.esm33_33,R.string.esm44_44,
                R.string.esm55_55,R.string.esm66_66,R.string.esm77_77,R.string.esm88_88,R.string.esm99_99,R.string.esm10101010,
                R.string.esm11111111,R.string.esm12121212,R.string.esm13131313,R.string.esm14141414,R.string.esm15151515,R.string.esm16161616,
                R.string.esm17171717,R.string.esm18181818,R.string.esm19191919,R.string.esm20202020,R.string.esm21212121,R.string.esm22222222,
                R.string.esm23232323,R.string.esm24242424,R.string.esm25252525,R.string.esm26262626,R.string.esm27272727,R.string.esm28282828,
                R.string.esm29292929,R.string.esm30303030,R.string.esm31313131,R.string.esm32323232, R.string.esm33333333, R.string.esm34343434,
                R.string.esm35353535,R.string.esm36363636,R.string.esm37373737,R.string.esm38383838,R.string.esm39393939,R.string.esm40404040,
                R.string.esm41414141,R.string.esm42424242,R.string.esm43434343,R.string.esm44444444,R.string.esm45454545,R.string.esm46464646,
                R.string.esm47474747,R.string.esm48484848,R.string.esm49494949,R.string.esm50505050,R.string.esm51515151,R.string.esm52525252,
                R.string.esm53535353,R.string.esm54545454,R.string.esm55555555,R.string.esm56565656,R.string.esm57575757,R.string.esm58585858,
                R.string.esm59595959,R.string.esm60606060,R.string.esm61616161,R.string.esm62626262,R.string.esm63636363,R.string.esm64464646,
                R.string.esm65656565,R.string.esm66666666,R.string.esm68686868,R.string.esm69696969,R.string.esm70707070,R.string.esm71717171,
                R.string.esm72727272,R.string.esm73737373,R.string.esm74747474,R.string.esm75757575,R.string.esm76767676, R.string.esm77777777,
                R.string.esm78787878,R.string.esm79797979,R.string.esm80808080,R.string.esm81818181,R.string.esm82828282,R.string.esm83838383,
                R.string.esm84848484,R.string.esm85858585,R.string.esm86868686,R.string.esm87878787,R.string.esm88888888,R.string.esm89898989,
                R.string.esm90909090,R.string.esm91919191,R.string.esm92929292,R.string.esm93939393,R.string.esm94949494,R.string.esm95959595,
                R.string.esm96969696,R.string.esm97979797,R.string.esm98989898,R.string.esm99999999};




        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }
        @SuppressLint("InflateParams") final View guillotineMenu =
                LayoutInflater.from(this).inflate(R.layout.guillotine2, null);
        root.addView(guillotineMenu);

        frst = findViewById(R.id.frst);
        frst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuillotineAnimation.close();
                FloatingToast.makeToast(Main2Activity.this, getResources().getString(R.string.profile), FloatingToast.LENGTH_TOO_LONG)
                        .setGravity(FloatingToast.GRAVITY_BOTTOM)
                        .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                        .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                        .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                        .setBackgroundBlur(true)
                        .setFloatDistance(FloatingToast.DISTANCE_LONG)
                        .setTextColor(Color.parseColor("#27a3dd"))
                        .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                        .show();    //Show toast at the specified fixed position
            }
        });
        frist1 = findViewById(R.id.frist1);
        frist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuillotineAnimation.close();
                FloatingToast.makeToast(Main2Activity.this, getResources().getString(R.string.profile), FloatingToast.LENGTH_TOO_LONG)
                        .setGravity(FloatingToast.GRAVITY_BOTTOM)
                        .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                        .setTextSizeInDp(getResources().getDimensionPixelSize(R.dimen._9mdp))
                        .setTextTypeface(Typeface.createFromAsset(getAssets(), "fonts/toto.ttf"))
                        .setBackgroundBlur(true)
                        .setFloatDistance(FloatingToast.DISTANCE_LONG)
                        .setTextColor(Color.parseColor("#27a3dd"))
                        .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                        .show();    //Show toast at the specified fixed position
            }
        });

        openk = MediaPlayer.create(this, R.raw.openk);
        closek = MediaPlayer.create(this, R.raw.closek);
        mGuillotineAnimation = new GuillotineAnimation.GuillotineBuilder
                (guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger2)
                        , contentHamburger)
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

        mMediaPlayer = MediaPlayer.create(this, R.raw.fato);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(false);
        mMediaPlayer.start();


        backb = MediaPlayer.create(this, R.raw.backb);


        for (int i = 0; i < lon_out.length; i++) {
            map = new HashMap<>();
            map.put(FoldingCellListAdapter.KEY_LON_OUT, lon_out[i]);
            map.put(FoldingCellListAdapter.KEY_LON_IN, lon_in[i]);
            map.put(FoldingCellListAdapter.KEY_IMAGE_IN, image_in[i]);
            map.put(FoldingCellListAdapter.KEY_NUMBERS_OUT, numbers_out[i]);
            map.put(FoldingCellListAdapter.KEY_NUMBERS_IN, numbers_in[i]);
            map.put(FoldingCellListAdapter.KEY_TEXT1OUT, text1out[i]);
            map.put(FoldingCellListAdapter.KEY_TEXT1IN, text1in[i]);
            map.put(FoldingCellListAdapter.KEY_TEXT1CONTENT, text1content[i]);
            map.put(FoldingCellListAdapter.KEY_TEXT2CONTENT, text2content[i]);
            map.put(FoldingCellListAdapter.KEY_TEXT3CONTENT, text3content[i]);

            cellList.add(map);
        }

        // get our list view
        ListView theListView = findViewById(R.id.mainListView);

        pages = MediaPlayer.create(this,R.raw.pages);

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, R.layout.cell,cellList);


        // set elements to adapter
        theListView.setAdapter(adapter);

        // set on click event listener to list view
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
                pages.start();
            }
        });

        badge = findViewById(R.id.badge);
        badge1 = findViewById(R.id.badge1);
        badge3 = findViewById(R.id.badge3);
        badge4 = findViewById(R.id.badge4);
        badge5 = findViewById(R.id.badge5);
        badge6 = findViewById(R.id.badge6);


        new ClickShrinkEffect(badge);
        new ClickShrinkEffect(badge1);
        new ClickShrinkEffect(badge3);
        new ClickShrinkEffect(badge4);
        new ClickShrinkEffect(badge5);
        new ClickShrinkEffect(badge6);



        menu1 = findViewById(R.id.menu1);
        new ClickShrinkEffect(menu1);
        if (context.getString(R.string.lang).equals("ar")) {

            menu1.setTypeface(FontManger.yad);
        }
        if(context.getString(R.string.lang).equals("zh")){

            menu1.setTypeface(FontManger.asian);
        }
        if(context.getString(R.string.lang).equals("cs")){

            menu1.setTypeface(FontManger.english);
        }
        if(context.getString(R.string.lang).equals("nl")){

            menu1.setTypeface(FontManger.english);
        }
        if(context.getString(R.string.lang).equals("en")){

            menu1.setTypeface(FontManger.english);
        }
        if(context.getString(R.string.lang).equals("fr")){

            menu1.setTypeface(FontManger.english);
        }
        if(context.getString(R.string.lang).equals("de")){

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("in")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("it")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("ja")) {

            menu1.setTypeface(FontManger.asian);
        }
        if (context.getString(R.string.lang).equals("ko")) {

            menu1.setTypeface(FontManger.asian);
        }
        if (context.getString(R.string.lang).equals("fa")) {

            menu1.setTypeface(FontManger.yad);
        }
        if (context.getString(R.string.lang).equals("pl")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("pt")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("ro")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("ru")) {

            menu1.setTypeface(FontManger.russi);
        }
        if (context.getString(R.string.lang).equals("es")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("th")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("tr")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("ms")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("hi")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("bn")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("sv")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("sq")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("az")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("bs")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("bg")) {

            menu1.setTypeface(FontManger.russi);
        }
        if (context.getString(R.string.lang).equals("ur")) {

            menu1.setTypeface(FontManger.yad);
        }
        if (context.getString(R.string.lang).equals("ha")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("no")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("so")) {

            menu1.setTypeface(FontManger.english);
        }
        if (context.getString(R.string.lang).equals("sw")) {

            menu1.setTypeface(FontManger.english);
        }

        GradientDrawable shapeDrawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.recv_bg_mic);
        shapeDrawable.setColor(getResources().getColor(R.color.settings_title_bg));
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

                CookieBar.build(Main2Activity.this)
                        .setTitle(getResources().getString(R.string.hold_button_to_record))
                        .setTitleColor(R.color.sholo)
                        .setMessage(getResources().getString(R.string.hold_button_to_record2))
                        .setMessageColor(R.color.default_message_color)
                        .setIcon(R.drawable.recv_ic_mic_white)
                        .setBackgroundColor(R.color.settings_title_bg)
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
                        .setBackgroundColor(R.color.settings_title_bg)
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
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        FloatingToast.makeToast(Main2Activity.this,  getResources().getString(R.string.finalback), FloatingToast.LENGTH_TOO_LONG)
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
