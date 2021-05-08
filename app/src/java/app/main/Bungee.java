package com.zaker.android.sapeh.app.main;

import android.app.Activity;
import android.content.Context;
import com.zaker.android.sapeh.R;

public class Bungee {

    public static void shrink(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.shrink_enter, R.anim.shrink_exit);
    }
    public static void maka(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit);
    }
    public static void souna(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
    }
}
