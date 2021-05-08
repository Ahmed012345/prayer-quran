package com.zaker.android.sapeh.app.main.activitymain.expandingcards;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;


import com.marcoscg.materialtoast.MaterialToast;
import com.zaker.android.sapeh.ARApplication;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.main.Bungee;
import com.zaker.android.sapeh.app.main.MainContract;
import com.zaker.android.sapeh.app.main.MainRecActivity;
import com.zaker.android.sapeh.app.main.activitymain.FontManger;
import com.zaker.android.sapeh.app.main.activitymain.LocaleHelper;
import com.zaker.android.sapeh.app.main.activitymain.expandingcards.pojo.CardData;
import com.zaker.android.sapeh.app.main.activitymain.expandingcards.view.ItemsCountView;
import com.zaker.android.sapeh.app.main.cookiebar.CookieBar;
import com.zaker.android.sapeh.app.main.expandinglibrary.ECCardData;
import com.zaker.android.sapeh.app.main.expandinglibrary.ECPagerView;
import com.zaker.android.sapeh.app.main.expandinglibrary.ECPagerViewAdapter;
import com.zaker.android.sapeh.app.main.foltingtoast.FloatingToast;
import com.zaker.android.sapeh.app.main.recordview.*;
import com.zaker.android.sapeh.util.AndroidUtils;


import java.util.concurrent.TimeUnit;

@SuppressLint("SetTextI18n")
public class Main5Activity extends Activity {

    public static final int REQ_CODE_REC_AUDIO_AND_WRITE_EXTERNAL = 101;
    public static final int REQ_CODE_RECORD_AUDIO = 303;
    public static final int REQ_CODE_WRITE_EXTERNAL_STORAGE = 404;
    public static final int REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT = 405;
    public static final int REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK = 406;
    public static final int REQ_CODE_IMPORT_AUDIO = 11;
    private MainContract.UserActionsListener presenter;
    MediaPlayer backb;
    RecordView recordView;
    RecordButton recordButton;
    Context context = this;
    boolean doubleBackToExitPressedOnce = false;


    private ECPagerView ecPagerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        FontManger.getInstance(getApplicationContext().getAssets());
        presenter = ARApplication.getInjector().provideMainPresenter();

        backb = MediaPlayer.create(this, R.raw.backb);

        GradientDrawable shapeDrawable = (GradientDrawable) ContextCompat.getDrawable(context, R.drawable.recv_bg_mic);
        shapeDrawable.setColor(getResources().getColor(R.color.fanlayout));
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

                CookieBar.build(Main5Activity.this)
                        .setTitle(getResources().getString(R.string.hold_button_to_record))
                        .setTitleColor(R.color.green)
                        .setMessage(getResources().getString(R.string.hold_button_to_record2))
                        .setMessageColor(R.color.default_message_color)
                        .setIcon(R.drawable.mico)
                        .setBackgroundColor(R.color.about_twitter_color)
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
                        .setBackgroundColor(R.color.fanlayout)
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

        // Create adapter for pager
        ECPagerViewAdapter adapter = new ECPagerViewAdapter(this, new ExampleDataset().getDataset()) {
            @Override
            public void instantiateCard(LayoutInflater inflaterService, ViewGroup head, ListView list, final ECCardData data) {
                final CardData cardData = (CardData) data;

                // Create adapter for list inside a card and set adapter to card content
                CommentArrayAdapter commentArrayAdapter = new CommentArrayAdapter(getApplicationContext(), cardData.getListItems());
                list.setAdapter(commentArrayAdapter);
                list.setDivider(getResources().getDrawable(R.drawable.list_divider));
                list.setDividerHeight((int) pxFromDp(getApplicationContext(), 0.5f));
                list.setSelector(R.color.transparent);
                list.setBackgroundColor(Color.WHITE);
                list.setCacheColorHint(Color.TRANSPARENT);

                // Add gradient to root header view
                View gradient = new View(getApplicationContext());
                gradient.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
                gradient.setBackgroundDrawable(getResources().getDrawable(R.drawable.card_head_gradient));
                head.addView(gradient);

                // Inflate main header layout and attach it to header root view
                inflaterService.inflate(R.layout.simple_head, head);

                // Set header data from data object


                // Add onclick listener to card header for toggle card state
                head.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        ecPagerView.toggle();
                    }
                });
            }
        };

        ecPagerView =  findViewById(R.id.ec_pager_element);

        ecPagerView.setPagerViewAdapter(adapter);
        ecPagerView.setBackgroundSwitcherView( findViewById(R.id.ec_bg_switcher_element));

        final ItemsCountView itemsCountView = findViewById(R.id.items_count_view);
        ecPagerView.setOnCardSelectedListener(new ECPagerView.OnCardSelectedListener() {
            @Override
            public void cardSelected(int newPosition, int oldPosition, int totalElements) {
                itemsCountView.update(newPosition, oldPosition, totalElements);
            }
        });



    }

     @Override
     protected void attachBaseContext(Context newBase) {
         super.attachBaseContext(LocaleHelper.onAttach(newBase));
     }

    @Override
    public void onBackPressed() {
        if (!ecPagerView.collapse())
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            backb.start();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        FloatingToast.makeToast(Main5Activity.this,  getResources().getString(R.string.finalback), FloatingToast.LENGTH_TOO_LONG)
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


    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
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
